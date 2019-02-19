package com.github.anddd7.leetcode.medium;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Integer2RomanTest {

  private Integer2Roman instance = new Integer2Roman();

  @Test
  void case1() {
    assertThat(instance.intToRoman(3999)).isEqualTo("MMMCMXCIX");
  }

  @Test
  void case2() {
    assertThat(instance.intToRoman(501)).isEqualTo("DI");
  }
}