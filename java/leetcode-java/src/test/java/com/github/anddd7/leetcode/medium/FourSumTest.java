package com.github.anddd7.leetcode.medium;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FourSumTest {

  private FourSum instance = new FourSum();

  @Test
  void case1() {
    assertThat(instance.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0)).hasSize(3);
  }

  @Test
  void case2() {
    assertThat(instance.fourSum(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0)).hasSize(8);
  }
}
