package com.github.anddd7.jdk8.concurrent;

import org.junit.jupiter.api.Test;

public class MemVisibilityTest {

  @Test
  public void test_indicator() throws InterruptedException {
    MemVisibilityNormal test = new MemVisibilityNormal();
    test.run();
  }

  @Test
  public void test_static_indicator() throws InterruptedException {
    MemVisibilityStatic test = new MemVisibilityStatic();
    test.run();
  }

  @Test
  public void test_volatile_indicator() throws InterruptedException {
    MemVisibilityVolatile test = new MemVisibilityVolatile();
    test.run();
  }

  @Test
  public void test_static_volatile_indicator() throws InterruptedException {
    MemVisibilityStaticVolatile test = new MemVisibilityStaticVolatile();
    test.run();
  }
}
