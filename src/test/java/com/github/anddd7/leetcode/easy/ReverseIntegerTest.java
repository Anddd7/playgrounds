package com.github.anddd7.leetcode.easy;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ReverseIntegerTest {

  private ReverseInteger instance = new ReverseInteger();

  @Test
  void case1() {
    assertThat(instance.reverse(-100)).isEqualTo(-1);
  }
}