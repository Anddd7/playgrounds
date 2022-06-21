package com.github.anddd7.leetcode.easy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RomanToIntegerTest {

  private RomanToInteger instance = new RomanToInteger();

  @Test
  void case1() {
    assertThat(instance.romanToInt("III")).isEqualTo(3);
  }

  @Test
  void case2() {
    assertThat(instance.romanToInt("LVIII")).isEqualTo(58);
  }

  @Test
  void case3() {
    assertThat(instance.romanToInt("MCMXCIV")).isEqualTo(1994);
  }
}