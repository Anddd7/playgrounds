package com.github.anddd7.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTest {

  @Test
  public void search() {
    Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    Assert.assertNull(BinarySearch.search_loop(array, 10));
    Assert.assertEquals(Integer.valueOf(7), BinarySearch.search_loop(array, 7));
    Assert.assertEquals(Integer.valueOf(5), BinarySearch.search_loop(array, 5));

    Assert.assertNull(BinarySearch.search_recursive(array, -5));
    Assert.assertEquals(Integer.valueOf(3), BinarySearch.search_recursive(array, 3));
    Assert.assertEquals(Integer.valueOf(8), BinarySearch.search_recursive(array, 8));
  }
}