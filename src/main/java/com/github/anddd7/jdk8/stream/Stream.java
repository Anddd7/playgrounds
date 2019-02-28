package com.github.anddd7.jdk8.stream;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Stream<E> {

  default <OUT> Stream<OUT> map(Function<E, OUT> mapper) {
    throw new UnsupportedOperationException();
  }

  default Stream<E> filter(Predicate<E> predicate) {
    throw new UnsupportedOperationException();
  }

  void forEach(Consumer<E> action);

  List<E> collectToList();
}

