package com.github.anddd7.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class BinarySearchTest {

  private Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

  @Test
  void search() {
    assertNull(BinarySearch.search_loop(array, 10));
    assertEquals(Integer.valueOf(7), BinarySearch.search_loop(array, 7));
    assertEquals(Integer.valueOf(5), BinarySearch.search_loop(array, 5));

    assertNull(BinarySearch.search_recursive(array, -5));
    assertEquals(Integer.valueOf(3), BinarySearch.search_recursive(array, 3));
    assertEquals(Integer.valueOf(8), BinarySearch.search_recursive(array, 8));
  }
}