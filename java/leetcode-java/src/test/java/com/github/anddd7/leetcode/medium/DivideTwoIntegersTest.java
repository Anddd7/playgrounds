package com.github.anddd7.leetcode.medium;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DivideTwoIntegersTest {

  private DivideTwoIntegers instance = new DivideTwoIntegers();

  @Test
  void case1() {
    assertThat(instance.divide(10, 3)).isEqualTo(3);
    assertThat(instance.divide(-10, -3)).isEqualTo(3);
    assertThat(instance.divide(10, -3)).isEqualTo(-3);
    assertThat(instance.divide(-10, 3)).isEqualTo(-3);
  }

  @Test
  void case2() {
    assertThat(instance.divide(-1, 1)).isEqualTo(-1);
  }

  @Test
  void case3() {
    assertThat(instance.divide(Integer.MIN_VALUE, -1)).isEqualTo(Integer.MAX_VALUE);
  }
}