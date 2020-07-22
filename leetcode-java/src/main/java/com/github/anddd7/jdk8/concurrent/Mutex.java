package com.github.anddd7.jdk8.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Mutex {

  /* SimplestSync */

  private static class SimplestSync extends AbstractQueuedSynchronizer {

    public SimplestSync() {
      setState(1);
    }
    /*
    public final void acquire(int arg) {
      // 只有一个线程能够成功
      if (!tryAcquire(arg) &&
          // 其他线程会以独占模式, 加入到队列尾
          // CAS
          Node waiter = addWaiter(Node.EXCLUSIVE)
          // 在队列中等待: 是拿到资源 ;park自己进入waiting状态
          // CAS
          acquireQueued(waiter, arg))
        selfInterrupt();
    }
    */

    @Override
    protected boolean tryAcquire(int arg) {
      return compareAndSetState(1, 0);
    }

    /*
    public final boolean release(int arg) {
      // 只有之前acquire的线程能够成功, 其他线程都还在waiting for acquire
      if (tryRelease(arg)) {
        Node h = head;
        if (h != null && h.waitStatus != 0)
          // 唤醒下一个node
          unparkSuccessor(h);
        return true;
      }
      return false;
    }
    */

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
    // 由AQS代理
    sync.acquire(1);
  }

  public void release() {
    sync.release(1);
  }
}
