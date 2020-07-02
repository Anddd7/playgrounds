package com.github.anddd7.coroutine;

import com.github.anddd7.coroutine.continuation.FinalContinuation;
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
