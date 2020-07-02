package com.github.anddd7.coroutine;

import org.junit.jupiter.api.Test;

class CoroutineDispatcherTest {

  private CoroutineDispatcher dispatcher;

  @Test
  public void should_resume_the_task_after_dispatched() throws InterruptedException {
    dispatcher = new CoroutineDispatcher();
    dispatcher.dispatch(
        new Continuation(() -> System.out.println("first task"))
    );
    dispatcher.dispatch(
        new Continuation(() -> System.out.println("second task"))
    );
    dispatcher.dispatch(
        new Continuation(() -> System.out.println("final task"), false)
    );

    // block main thread
    Thread.sleep(10000);
  }
}
