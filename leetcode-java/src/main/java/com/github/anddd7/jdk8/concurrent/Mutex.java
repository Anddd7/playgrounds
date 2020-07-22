package com.github.anddd7.jdk8.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Mutex {

  /* SimplestSync */

  private static class SimplestSync extends AbstractQueuedSynchronizer {

    public SimplestSync() {
      setState(1);
    }

    @Override
    protected boolean tryAcquire(int arg) {
      return compareAndSetState(1, 0);
    }

    @Override
    protected boolean tryRelease(int arg) {
      return compareAndSetState(0, 1);
    }
  }

  public static Mutex simplest() {
    return new Mutex(new SimplestSync());
  }

  /* interface methods */

  private final AbstractQueuedSynchronizer sync;

  private Mutex(AbstractQueuedSynchronizer sync) {
    this.sync = sync;
  }

  public void acquire() {
    sync.acquire(1);
  }

  public void release() {
    sync.release(1);
  }
}
