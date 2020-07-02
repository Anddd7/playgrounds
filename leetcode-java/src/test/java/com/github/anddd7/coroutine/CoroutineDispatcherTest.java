package com.github.anddd7.coroutine;

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
        int count = getCount();
        count = increase(count);
        count = increase(count);
        return count;
      };
      return result;
    }
    coroutine getCount(){
      return 1;
    }
    coroutine increase(int number){
      return number+1;
    }
    */
    Continuation task = new Continuation() {
      private int state = 0;
      private final ConcurrentMap<String, Object> data = new ConcurrentHashMap<>();

      @Override
      public Continuation resumeWith() {
        switch (state) {
          case 0:
            System.out.println(Thread.currentThread().getName() + ": getCount");
            data.put("number", 1);
            state += 1;
            return this;
          case 1:
            System.out.println(Thread.currentThread().getName() + ": increase");
            data.compute("number", (key, value) -> (int) value + 1);
            state += 1;
            return this;
          case 2:
            System.out.println(Thread.currentThread().getName() + ": increase");
            data.compute("number", (key, value) -> (int) value + 1);
          default:
            complete(data.get("number"));
            return null;
        }
      }
    };

    dispatcher = new CoroutineDispatcher();
    CompletableFuture<Object> future = dispatcher.dispatch(task);
    future.get();
  }
}
