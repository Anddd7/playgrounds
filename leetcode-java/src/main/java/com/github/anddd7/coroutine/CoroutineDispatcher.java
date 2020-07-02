package com.github.anddd7.coroutine;

import java.util.Deque;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        log.info("empty queue, skip");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        Continuation continuation = continuations.pop();
        log.info("find a continuation, submit");

        executor.submit(wrapTask(continuation));
      }
    }
  }

  private Runnable wrapTask(Continuation continuation) {
    return () -> {
      log.info("start task");
      Continuation next = continuation.resumeWith();
      if (next != null) {
        dispatch(next);
      }
      if (continuation.isCompleted()) {
        log.info("task is completed");
      }
    };
  }

  public CompletableFuture<Object> dispatch(Continuation continuation) {
    log.info("dispatch a new continuation");
    continuations.add(continuation);
    return continuation.getHook();
  }
}
