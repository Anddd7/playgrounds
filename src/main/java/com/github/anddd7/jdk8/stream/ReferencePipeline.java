package com.github.anddd7.jdk8.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @param <E> 当前元素类型
 * @param <ROOT> 根元素类型
 */
public abstract class ReferencePipeline<E, ROOT> implements Stream<E>, Pipeline {

  private ReferencePipeline<?, ROOT> root;
  private ReferencePipeline<?, ROOT> upstream;
  private ReferencePipeline<?, ROOT> downstream;
  private Iterator<ROOT> iterator;

  public ReferencePipeline(Iterator<ROOT> iterator) {
    this.root = this;
    this.iterator = iterator;
  }

  public ReferencePipeline(ReferencePipeline<?, ROOT> upstream) {
    this.root = upstream.root;
    this.upstream = upstream;
    this.downstream = null;
    upstream.downstream = this;
  }

  @Override
  public <R> Stream<R> map(Function<E, R> mapper) {
    // next stream
    return new ReferencePipeline<R, ROOT>(this) {
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
  public Stream<E> filter(Predicate<E> predicate) {
    return new ReferencePipeline<E, ROOT>(this) {
      // overwrite method to wrap current sink
      @Override
      public Sink opWrapSink(Sink sink) {

        return new ReferenceSink<E, E>(sink) {
          // overwrite method to pass mapped value to next sink
          @Override
          public void accept(E value) {
            if (predicate.test(value)) {
              this.downstream.accept(value);
            }
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
    final Sink<ROOT> finalWrapped = wrapSinks(endSink);
    root.iterator.forEachRemaining(finalWrapped::accept);
  }

  @Override
  public List<E> collectToList() {
    final List<E> list = new ArrayList<>();
    forEach(list::add);
    return list;
  }

  private <R> Sink<R> wrapSinks(Sink<E> sink) {
    for (ReferencePipeline p = this; p != root; p = p.upstream) {
      sink = p.opWrapSink(sink);
    }
    return (Sink<R>) sink;
  }
}
