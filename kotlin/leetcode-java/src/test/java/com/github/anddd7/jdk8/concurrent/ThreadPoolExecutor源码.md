# ThreadPoolExecutor.java

- TPE使用`ctl`同时存放状态和可用线程数，初始状态下：
    - COUNT_BITS=29
    - `00011111 11111111 11111111 11111111`=CAPACITY
    - `11100000 00000000 00000000 00000000`=RUNNING
    - `00000000 00000000 00000000 00000000`=SHUTDOWN
    - `00100000 00000000 00000000 00000000`=STOP
    - `01000000 00000000 00000000 00000000`=TIDYING
    - `01100000 00000000 00000000 00000000`=TERMINATED
    - `11100000 00000000 00000000 00000000`=ctl初始值

__即 32-30 存放状态；29-1 存放当前线程数__

```java
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
```

- 状态和线程数计算

```java
    // `ctl & ~CAPACITY` 获取状态
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    // `ctl & CAPACITY` 获取线程数
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    // `ctl | workerCount` 获取新的ctl值
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    // 判断线程池是否运行
    private static boolean isRunning(int c) {
        return c < SHUTDOWN;
    }
```

- execute 提交任务
```java
    public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();
        int c = ctl.get();
       
        // 1. 当前运行线程小于核心线程数, 创建新的线程worker并执行
        if (workerCountOf(c) < corePoolSize) {
            if (addWorker(command, true))
                return;
            // 创建失败, 重读当前ctl
            c = ctl.get();
        }
        
        // 2. 如果线程池仍运行, 就把任务加到队列中
        if (isRunning(c) && workQueue.offer(command)) {
            // 重复check ctl
            int recheck = ctl.get();
            
            // 如果线程池不再可用, 就移除刚加入的任务
            // 因为这里是无锁的操作, 因此可能线程1提交了任务还未执行, 线程2就直接shutdown了线程池
            // 为了尽早的移除任务, 这里使用double check进行操作
            if (!isRunning(recheck) && remove(command))
                reject(command);
            // 如果当前线程池为空就再次添加worker稍后执行(但不会立即assign task给新的worker)
            else if (workerCountOf(recheck) == 0)
                addWorker(null, false);
        }
        // 3. queue失败, 立即申请一个thread来执行这个任务
        // 如果失败, reject
        else if (!addWorker(command, false))
            reject(command);
    }
```
