package com.github.anddd7.leetcode.easy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LongestCommonPrefixTest {

  private LongestCommonPrefix instance = new LongestCommonPrefix();

  @Test
  void case1() {
    assertThat(instance.longestCommonPrefix(new String[]{"flower", "flow", "flight"})).isEqualTo("fl");
  }

  @Test
  void case2() {
    assertThat(instance.longestCommonPrefix(new String[]{"", ""})).isEqualTo("");
  }

  @Test
  void case3() {
    assertThat(instance.longestCommonPrefix(new String[]{"c", "c"})).isEqualTo("c");
  }
}