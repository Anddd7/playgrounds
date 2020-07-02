package com.github.anddd7.coroutine;

import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CoroutineDispatcher {

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

  private void log(String msg) {
    System.out.printf(
        "[%s]%s%n",
        Thread.currentThread().getName(),
        msg
    );
  }

  private void tryResume() {
    log("start scanning continuation queue");

    while (true) {
      if (continuations.isEmpty()) {
        log("empty queue, skip");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        Continuation continuation = continuations.pop();
        log("find a continuation, submit");

        executor.submit(wrapTask(continuation));
      }
    }
  }

  private Runnable wrapTask(Continuation continuation) {
    return () -> {
      log("start task");
      continuation.getRunnable().run();
    };
  }

  public void dispatch(Continuation continuation) {
    log("dispatch a new continuation");

    continuations.add(continuation);
  }
}
