package com.github.anddd7.jdk8.generics;

import java.util.Date;

public class Pair<T> {

  private T obj;

  public Pair(T obj) {
    this.obj = obj;
  }

  public T getObj() {
    return obj;
  }

  public void setObj(T obj) {
    this.obj = obj;
  }
}

