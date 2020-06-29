package com.github.anddd7.datastructure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleMinHeapTest {

  @Test
  public void test0() {
    SimpleMinHeap heap = new SimpleMinHeap(6);
    heap.add(3);
    heap.add(2);
    heap.add(1);
    heap.add(5);
    heap.add(6);
    heap.add(4);

    Assertions.assertThat(heap.peek()).isEqualTo(1);
    Assertions.assertThat(heap.toString()).isEqualTo("[1, 3, 2, 5, 6, 4]");
  }
}
