package com.github.anddd7.jdk8.concurrent;

public class AsyncTest {

  public static void main(String[] args) throws InterruptedException {
    async();
  }

  private static void sync() throws InterruptedException {
    Thread child = new Thread(() -> {
      System.out.println("doing task 2");
      System.out.println("doing task 2.1");
    });
    System.out.println("doing task 1");
    child.start(); // start task 2
    child.join(); // wait for task 2
    System.out.println("doing task 3");
  }

  private static void async() throws InterruptedException {
    Thread child = new Thread(() -> {
      System.out.println("doing task 2");
      System.out.println("doing task 2.1");
      ThreadLocal<String> stringThreadLocal = ThreadLocal.withInitial(() -> "test");
      stringThreadLocal.set();
      stringThreadLocal.get();
      stringThreadLocal.remove();
    });
    System.out.println("doing task 1");
    child.start(); // start task 2
    System.out.println("doing task 3");
    child.join(); // wait for task 2
  }
}
