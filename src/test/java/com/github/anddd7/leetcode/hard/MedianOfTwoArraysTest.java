package com.github.anddd7.leetcode.hard;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class MedianOfTwoArraysTest {

  private MedianOfTwoArrays instance = new MedianOfTwoArrays();


  @Test
  void case1() {
    assertThat(
        instance.findMedianSortedArrays(
            new int[]{1, 2, 3, 4},
            new int[]{2, 3, 4, 5}
        )
    ).isEqualTo(3);
  }

  @Test
  void case2() {
    assertThat(
        instance.findMedianSortedArrays(
            new int[]{1, 3},
            new int[]{2}
        )
    ).isEqualTo(2);
  }
}