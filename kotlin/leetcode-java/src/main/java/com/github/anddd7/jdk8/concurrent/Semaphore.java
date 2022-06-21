package com.github.anddd7.jdk8.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Semaphore {

  /* Sync */

  private static class Sync extends AbstractQueuedSynchronizer {

    public Sync(int resources) {
      setState(resources);
    }

    @Override
    protected int tryAcquireShared(int arg) {
      int available = getState();
      int remaining = available - arg;
      if (remaining < 0 || compareAndSetState(available, remaining)) {
        return remaining;
      }
      return -1;
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
      int current = getState();
      int next = current + arg;
      return compareAndSetState(current, next);
    }
  }

  public static Semaphore semaphore(int resources) {
    return new Semaphore(resources);
  }

  /* interface methods */

  private final Sync sync;

  private Semaphore(int resources) {
    this.sync = new Sync(resources);
  }

  public void acquire() {
    sync.acquireShared(1);
  }

  public void release() {
    sync.releaseShared(1);
  }
}
