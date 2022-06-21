package com.github.anddd7.leetcode.medium;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class String2IntTest {

  private String2Int instance = new String2Int();

  @Test
  void case1() {
    assertThat(instance.myAtoi(" +0   -123")).isEqualTo(0);
  }

  @Test
  void case2() {
    assertThat(instance.myAtoi("0-1")).isEqualTo(0);
  }

  @Test
  void case3() {
    assertThat(instance.myAtoi("4193 with words")).isEqualTo(4193);
  }

  @Test
  void case4() {
    assertThat(instance.myAtoi("+")).isEqualTo(0);
  }
}