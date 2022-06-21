package com.github.anddd7.jdk8.concurrent;

public class SynchronizedTest {

  public synchronized void willLockTheObject() {
  }

  public synchronized static void willLockTheClass() {
  }

  public void willLockCodeBlock() {
    synchronized (this) {
      System.out.println("im in synchronized block");
    }
  }

  public void willLockCodeBlockWithSpecificObject() {
    Object lock = new Object();
    synchronized (lock) {
      System.out.println("im in synchronized block");
    }
  }


  private volatile static SynchronizedTest instance;

  private SynchronizedTest() {
  }

  /**
   * 双重校验生成单例
   */
  public static synchronized SynchronizedTest getInstance() {
    // 1 非null时直接返回, 只锁定创建过程
    if (instance == null) {
      synchronized (SynchronizedTest.class) {
        // 2 避免多线程同时通过第一层校验, 进行2次校验
        if (instance == null) {
          instance = new SynchronizedTest();
        }
      }
    }
    return instance;
  }
}
