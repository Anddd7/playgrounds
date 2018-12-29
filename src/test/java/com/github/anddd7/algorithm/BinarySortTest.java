package com.github.anddd7.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class BinarySortTest {

  private Integer[] array = new Integer[]{1, 3, 20, 4, 53, 16, 7, 48, 9};

  @Test
  void sort() {
    assertEquals("[1, 3, 4, 7, 9, 16, 20, 48, 53]", Arrays.toString(BinarySort.sort_loop(array))
    );
  }
}