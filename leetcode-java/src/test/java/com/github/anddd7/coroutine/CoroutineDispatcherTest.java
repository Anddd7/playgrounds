package com.github.anddd7.coroutine;

import com.github.anddd7.coroutine.continuation.DelayContinuation;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

class CoroutineDispatcherTest {

  private CoroutineDispatcher dispatcher;

  @Test
  public void should_resume_the_task_after_dispatched()
      throws InterruptedException, ExecutionException {
    /*
    int test() {
      int result = blocking {
        delay(500)
        int count = 1;
        count = number+1;
        count = number+1;
        return count;
      };
      return result;
    }
    */

    ContinuationImpl task = new ContinuationImpl() {
      private int state = 0;
      private final ConcurrentMap<String, Object> data = new ConcurrentHashMap<>();
      private Object result;

      @Override
      public Continuation resumeWith(Continuation continuation) {
        setSuspended(false);
        switch (state) {
          case 0:
            System.out.println(Thread.currentThread().getName() + ": getCount");
            data.put("number", 1);
            state += 1;
            return this;
          case 1:
            ContinuationImpl delay = new DelayContinuation();
            delay.setDispatcher(getDispatcher());
            delay.setCompletion(this);
            getDispatcher().dispatch(delay);
            setSuspended(true);
            state += 1;
            return this;
          case 2:
            System.out.println(Thread.currentThread().getName() + ": increase");
            data.compute("number", (key, value) -> (int) value + 1);
            state += 1;
            return this;
          case 3:
            System.out.println(Thread.currentThread().getName() + ": increase");
            data.compute("number", (key, value) -> (int) value + 1);
          default:
            System.out.println(Thread.currentThread().getName() + ": finished");
            result = data.get("number");
            complete();
            return null;
        }
      }

      @Override
      public Object getResult() {
        return result;
      }
    };

    dispatcher = new CoroutineDispatcher();

    CompletableFuture<Object> blocking = dispatcher.start(task);
    blocking.get();
  }
}
