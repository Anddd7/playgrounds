package com.github.anddd7.jdk8.stream;

/**
 * @param <E> 当前元素类型
 * @param <OUT> 下一个Pipeline所接受的类型
 */
public interface Pipeline<E, OUT> {

  /**
   * 倒序连接sink
   *
   * @param sink 下一个pipeline的处理器
   */
  Sink<E> opWrapSink(Sink<OUT> sink);
}