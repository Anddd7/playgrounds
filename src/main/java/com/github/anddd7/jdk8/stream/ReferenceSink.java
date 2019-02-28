package com.github.anddd7.jdk8.stream;

/**
 * @param <E> 当前处理元素的类型
 * @param <OUT> 传递出去的元素类型
 */
public abstract class ReferenceSink<E, OUT> implements Sink<E> {

  protected Sink<OUT> downstream;

  public ReferenceSink(Sink<OUT> downstream) {
    this.downstream = downstream;
  }
}