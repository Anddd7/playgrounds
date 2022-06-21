package com.github.anddd7.jdk8.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class SemaphoreTest {

  @Test
  public void test_semaphore() throws InterruptedException {
    Semaphore semaphore = Semaphore.semaphore(2);
    ExecutorService executorService = new ThreadPoolExecutor(
        5, 5, 1000, TimeUnit.MILLISECONDS,
        new ArrayBlockingQueue<>(100)
    );

    for (int i = 0; i < 5; i++) {
      executorService.submit(() -> {
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName() + " Doing");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Finished");
        semaphore.release();
      });
    }

    executorService.shutdown();
    while (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
      System.out.println("waiting~");
    }
  }
}
