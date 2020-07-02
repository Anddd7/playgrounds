package com.github.anddd7.coroutine;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.jupiter.api.Test;

class CoroutineDispatcherTest {

  private CoroutineDispatcher dispatcher;

  @Test
  public void should_resume_the_task_after_dispatched() throws InterruptedException {
    Continuation task = new Continuation() {
      private int state = 0;
      private final ConcurrentMap<String, Object> data = new ConcurrentHashMap<>();

      @Override
      public Continuation resumeWith() {
        switch (state) {
          case 0:
            System.out.println(Thread.currentThread().getName() + ": first task");
            data.put("number", 1);
            state += 1;
            return this;
          case 1:
            System.out.println(Thread.currentThread().getName() + ": second task");
            data.compute("number", (key, value) -> (int) value + 1);
            state += 1;
            return this;
          case 2:
            System.out.println(Thread.currentThread().getName() + ": final task");
            data.compute("number", (key, value) -> (int) value + 1);
          default:
            return null;
        }
      }
    };

    dispatcher = new CoroutineDispatcher();
    dispatcher.dispatch(task);

    // block main thread
    Thread.sleep(10000);
  }
}
