package com.github.anddd7.jdk8.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class MutexTest {

  @Test
  public void test_mutex() throws InterruptedException {
    Mutex mutex = Mutex.simplest();
    ExecutorService executorService = new ThreadPoolExecutor(
        5, 5, 1000, TimeUnit.MILLISECONDS,
        new ArrayBlockingQueue<>(100)
    );

    for (int i = 0; i < 5; i++) {
      executorService.submit(() -> {
        mutex.acquire();
        System.out.println(Thread.currentThread().getName() + " Doing");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Finished");
        mutex.release();
      });
    }

    executorService.shutdown();
    while (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
      System.out.println("waiting~");
    }
  }
}
