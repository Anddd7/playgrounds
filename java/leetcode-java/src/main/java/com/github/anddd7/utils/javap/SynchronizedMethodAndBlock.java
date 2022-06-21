package com.github.anddd7.utils.javap;

@SuppressWarnings("all")
public class SynchronizedMethodAndBlock {

  private Object mutex;
  private int a;
  private volatile int b;

  public synchronized void methodA() {
    a = 10;
  }

  public void methodA1() {
    synchronized (this) {
      a = 10;
    }
  }


  public void methodA2() {
    synchronized (mutex) {
      a = 10;
    }
  }

  public synchronized void methodB() {
    b = 10;
  }

  public void methodB1() {
    synchronized (this) {
      b = 10;
    }
  }


  public void methodB2() {
    synchronized (mutex) {
      b = 10;
    }
  }
}
