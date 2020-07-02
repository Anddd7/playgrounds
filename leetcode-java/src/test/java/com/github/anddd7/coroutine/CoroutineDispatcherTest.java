package com.github.anddd7.coroutine;

import org.junit.jupiter.api.Test;

class CoroutineDispatcherTest {

  private CoroutineDispatcher dispatcher;

  @Test
  public void should_resume_the_task_after_dispatched() throws InterruptedException {
    Continuation finalTask = new Continuation() {
      @Override
      public Continuation resumeWith(Object parameters) {
        System.out.println(Thread.currentThread().getName() + ": final task");
        return null;
      }
    };
    Continuation secondTask = new Continuation() {
      @Override
      public Continuation resumeWith(Object parameters) {
        System.out.println(Thread.currentThread().getName() + ": second task");
        return finalTask;
      }
    };
    Continuation firstTask = new Continuation() {
      @Override
      public Continuation resumeWith(Object parameters) {
        System.out.println(Thread.currentThread().getName() + ": first task");
        return secondTask;
      }
    };

    dispatcher = new CoroutineDispatcher();
    dispatcher.dispatch(firstTask);

    // block main thread
    Thread.sleep(10000);
  }
}
