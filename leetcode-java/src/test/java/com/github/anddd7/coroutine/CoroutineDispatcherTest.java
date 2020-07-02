package com.github.anddd7.coroutine;

import org.junit.jupiter.api.Test;

class CoroutineDispatcherTest {

  private CoroutineDispatcher dispatcher;

  @Test
  public void should_resume_the_task_after_dispatched() throws InterruptedException {
    Continuation task = new Continuation() {
      private int state = 0;

      @Override
      public Continuation resumeWith() {
        switch (state) {
          case 0:
            System.out.println(Thread.currentThread().getName() + ": first task");
            state += 1;
            return this;
          case 1:
            System.out.println(Thread.currentThread().getName() + ": second task");
            state += 1;
            return this;
          case 2:
            System.out.println(Thread.currentThread().getName() + ": final task");
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
