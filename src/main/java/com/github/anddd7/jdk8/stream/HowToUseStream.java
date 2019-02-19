package com.github.anddd7.jdk8.stream;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Stream is a useful feature in java8.
 * But it doesn't improve your efficiency every time.
 * If you don't know how to use it in a right way.
 *
 * A simple implement, according to [CarpenterLee/JavaLambdaInternals](https://github.com/CarpenterLee/JavaLambdaInternals/blob/master/6-Stream%20Pipelines.md)
 */
class HowToUseStream {

  interface Stream<E> {

    <OUT> Stream<OUT> map(Function<E, OUT> mapper);

    void forEach(Consumer<E> action);
  }

  interface Pipeline<E, OUT> {

    Sink<E> opWrapSink(Sink<OUT> sink);
  }


  interface Sink<E> {

    void accept(E value);
  }

  abstract class ReferenceSink<E, OUT> implements Sink<E> {

    protected Sink<OUT> downstream;

    public ReferenceSink(Sink<OUT> downstream) {
      this.downstream = downstream;
    }
  }

  abstract class ReferencePipeline<E, S> implements Stream<E>, Pipeline {

    private ReferencePipeline<?, S> root;
    private ReferencePipeline upstream;
    private ReferencePipeline downstream;
    private Iterator<S> iterator;

    public ReferencePipeline(Iterator<S> iterator) {
      this.root = this;
      this.iterator = iterator;
    }

    public ReferencePipeline(ReferencePipeline upstream) {
      this.root = upstream.root;
      this.upstream = upstream;
      this.downstream = null;
      upstream.downstream = this;
    }

    @Override
    public <R> Stream<R> map(Function<E, R> mapper) {
      // next stream
      return new ReferencePipeline(this) {
        // overwrite method to wrap current sink
        @Override
        public Sink opWrapSink(Sink sink) {

          return new ReferenceSink<E, R>(sink) {
            // overwrite method to pass mapped value to next sink
            @Override
            public void accept(E value) {
              this.downstream.accept(mapper.apply(value));
            }
          };
        }
      };
    }

    @Override
    public void forEach(Consumer<E> action) {
      final Sink<E> endSink = new Sink<E>() {
        @Override
        public void accept(E value) {
          action.accept(value);
        }
      };
      final Sink<S> finalWrapped = wrapSinks(endSink);
      root.iterator.forEachRemaining(finalWrapped::accept);
    }

    private <R> Sink<R> wrapSinks(Sink<E> sink) {
      for (ReferencePipeline p = this; p != root; p = p.upstream) {
        sink = p.opWrapSink(sink);
      }
      return (Sink<R>) sink;
    }
  }
}
