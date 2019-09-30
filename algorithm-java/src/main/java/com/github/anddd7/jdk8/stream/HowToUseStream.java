package com.github.anddd7.jdk8.stream;

import java.util.Collection;

/**
 * Stream is a useful feature in java8.
 * But it doesn't improve your efficiency every time.
 * If you don't know how to use it in a right way.
 *
 * A simple implement, according to [CarpenterLee/JavaLambdaInternals](https://github.com/CarpenterLee/JavaLambdaInternals/blob/master/6-Stream%20Pipelines.md)
 */
public class HowToUseStream {

  static public <E> Stream<E> buildStream(Collection<E> c) {
    return new ReferencePipeline<E, E>(c.iterator()) {
      @Override
      public Sink opWrapSink(Sink sink) {
        return null;
      }
    };
  }
}
