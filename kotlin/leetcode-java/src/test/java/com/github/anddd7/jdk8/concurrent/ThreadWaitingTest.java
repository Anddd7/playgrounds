package com.github.anddd7.jdk8.concurrent;

public class ThreadWaitingTest {

  public static void main(String[] args) throws InterruptedException {
    Object product = new Object();
    Thread child = new Thread(() -> {
      synchronized (product) {
        System.out.println("worker get the product");
        System.out.println("worker is working~");
        System.out.println("worker has finished his work");
        // reporting to boss
        product.notify();
        try {
          // waiting for payroll
          product.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("worker get the payroll");
      }
    });
    child.start();

    synchronized (product) {
      System.out.println("boss plan to produce a product");
      // waiting for worker
      product.wait();
      System.out.println("boss get the product");
      System.out.println("boss is checking~");
      System.out.println("boss give the money");
      product.notify();
      System.out.println("boss get good feedback");
    }

    child.join();
  }
}
