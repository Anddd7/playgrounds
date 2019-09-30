package com.github.anddd7.jdk8.stream;

public interface Sink<E> {

  void accept(E value);
}