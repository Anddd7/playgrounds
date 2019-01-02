package com.github.anddd7.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class LimitedArrayQueueTest {

  private LimitedArrayQueue<String> limitedArrayQueue = new LimitedArrayQueue<>(3);

  @Test
  void pushAndPoll() {
    limitedArrayQueue.push("1");
    limitedArrayQueue.push("2");
    limitedArrayQueue.push("3");
    limitedArrayQueue.push("4");
    limitedArrayQueue.push("5");

    assertEquals("1", limitedArrayQueue.poll());
    assertEquals("2", limitedArrayQueue.poll());
    assertEquals("3", limitedArrayQueue.poll());
    assertEquals("4", limitedArrayQueue.poll());
    assertNull(limitedArrayQueue.poll());
  }
}