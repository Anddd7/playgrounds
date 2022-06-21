package com.github.anddd7.jdk8.concurrent;

public class MemVisibilityVolatile {

  private volatile boolean indicator = false;

  private void emptyWorkerTask() {
    System.out.printf(
        "%s: I'm %s, got indicator=%s%n",
        System.currentTimeMillis(),
        Thread.currentThread().getName(),
        indicator
    );
    while (!indicator) {
    }
    System.out.printf(
        "%s: I'm %s, bye bye%n",
        System.currentTimeMillis(),
        Thread.currentThread().getName()
    );
  }

  private void workerTask() {
    System.out.printf(
        "%s: I'm %s, got indicator=%s%n",
        System.currentTimeMillis(),
        Thread.currentThread().getName(),
        indicator
    );
    int i = 0;
    while (!indicator) {
      i++;
    }
    System.out.printf(
        "%s: I'm %s, bye bye, finished: %d%n",
        System.currentTimeMillis(),
        Thread.currentThread().getName(),
        i
    );
  }

  public void run() throws InterruptedException {
    Thread worker = new Thread(this::workerTask, "worker");
    Thread emptyWorker = new Thread(this::emptyWorkerTask, "empty-worker");
    worker.start();
    emptyWorker.start();

    System.out.printf(
        "%s: I'm %s, got indicator=%s%n",
        System.currentTimeMillis(),
        Thread.currentThread().getName(),
        indicator
    );
    Thread.sleep(100);
    indicator = true;
    System.out.printf(
        "%s: I'm %s, bye bye%n",
        System.currentTimeMillis(),
        Thread.currentThread().getName()
    );

    worker.join(1000 * 2);
    emptyWorker.join(1000 * 2);
  }
}
