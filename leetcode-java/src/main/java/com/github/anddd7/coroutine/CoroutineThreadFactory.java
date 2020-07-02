package com.github.anddd7.coroutine;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CoroutineThreadFactory implements ThreadFactory {

  private final ThreadGroup group;
  private final AtomicInteger threadNumber = new AtomicInteger(1);
  private final String namePrefix;

  public CoroutineThreadFactory() {
    SecurityManager s = System.getSecurityManager();
    this.group = (s != null) ?
        s.getThreadGroup() :
        Thread.currentThread().getThreadGroup();
    this.namePrefix = "coroutine-thread-";
  }

  @Override
  public Thread newThread(Runnable runnable) {
    String threadName = namePrefix + threadNumber.getAndIncrement();
    Thread t = new Thread(group, runnable, threadName, 0);
    if (t.isDaemon()) {
      t.setDaemon(false);
    }
    if (t.getPriority() != Thread.NORM_PRIORITY) {
      t.setPriority(Thread.NORM_PRIORITY);
    }
    return t;
  }
}
