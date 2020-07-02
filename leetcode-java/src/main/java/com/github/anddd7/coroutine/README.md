## Coroutine原理(基于CPS)

假设我们要运行一段这样的(伪)Kotlin代码, 我们希望blocking内的代码能够以协程的方式执行:
```kotlin
fun test():Int {
  val result = runBlocking {
    delay(500)
    val count = 1
    count = launch { number+1 }
    count = launch { number+1 }
    count
  }
  return result
}
```

基于CPS, 编译器会把代码编译成这样(java sample):
```java
new ContinuationImpl() {
  // 流转的状态  
  private int state = 0;
  // 存放协程内的变量数据
  private final ConcurrentMap<String, Object> data = new ConcurrentHashMap<>();
  // 返回值
  private Object result;

  @Override
  public Continuation resumeWith(Continuation continuation) {
    switch (state) {
      case 0:
        data.put("number", 1);
        state += 1;
        return this;
      case 1:
        ContinuationImpl delay = new DelayContinuation();
        // ...省略先
        state += 1;
        return this;
      case 2:
        data.compute("number", (key, value) -> (int) value + 1);
        state += 1;
        return this;
      case 3:
        data.compute("number", (key, value) -> (int) value + 1);
      default:
        result = data.get("number");
        complete();
        return null;
    }
  }
```

即所有的数据和代码都会被包裹在一个Continuation里面, 要想他运行在不同的线程里就需要Dispatcher来分配工作了

```java
public class CoroutineDispatcher {

  private static final Logger log = LoggerFactory.getLogger(CoroutineDispatcher.class);

  private final ExecutorService executor;
  private final Deque<Continuation> continuations;
  private final Thread scheduler;

  public CoroutineDispatcher() {
    executor = new ThreadPoolExecutor(
        5, 5, 0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(),
        new CoroutineThreadFactory()
    );
    continuations = new LinkedBlockingDeque<>();
    scheduler = new Thread(this::tryResume, "scheduler");
    scheduler.start();
  }

  private void tryResume() {
    log.info("start scanning continuation queue");

    while (true) {
      if (continuations.isEmpty()) {
        rest4While();
      } else {
        Continuation continuation = continuations.pop();
        if (continuation.isSuspended()) {
          dispatch(continuation);
        } else {
          log.info("find a live continuation, submit");
          executor.submit(wrapTask(continuation));
        }
      }
    }
  }

  private void rest4While() {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private Runnable wrapTask(Continuation continuation) {
    return () -> {
      Continuation next = continuation.resumeWith(null);
      if (next != null) {
        dispatch(next);
      }
    };
  }

  public void dispatch(Continuation continuation) {
    continuations.add(continuation);
  }

  public CompletableFuture<Object> start(ContinuationImpl continuation) {
    FinalContinuation finalContinuation = new FinalContinuation();
    continuation.setCompletion(finalContinuation);
    continuation.setDispatcher(this);
    continuations.add(continuation);
    return finalContinuation.getHook();
  }
}
```

dispatcher主要的工作就是安排continuation, 何时运行/何时等待/何时进入下一轮:
[!img](img/coroutine-dispatcher.png)

最后通过Completion的hook回到主线程

## 嵌套
这里我们发现delay其实也是一个协程函数, 他会阻塞当前协程, 但是我们又不想他真的阻塞当前运行这个协程的线程;
- 所以我们会suspend当前协程, 使其不能再被scheduler扫描到
- 然后开启另一个协程(线程)去执行delay
- 完成过后在resume被suspend的协程继续进行

如果你使用的scheduled task, 那么在没有任何线程被阻塞的情况下, 你依旧可以完成这个任务
