package com.github.anddd7.leetcode.easy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RemoveDuplicatesFromSortedArrayTest {

  private RemoveDuplicatesFromSortedArray instance = new RemoveDuplicatesFromSortedArray();

  @Test
  void case1() {
    int[] array = new int[]{1, 1, 2};
    Assertions.assertThat(instance.removeDuplicates(array)).isEqualTo(2);
    Assertions.assertThat(array[0]).isEqualTo(1);
    Assertions.assertThat(array[1]).isEqualTo(2);
  }
}