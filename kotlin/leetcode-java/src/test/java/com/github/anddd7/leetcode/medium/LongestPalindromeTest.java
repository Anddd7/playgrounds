package com.github.anddd7.leetcode.medium;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LongestPalindromeTest {

  private LongestPalindrome instance = new LongestPalindrome();

  @Test
  void case1() {
    assertThat(instance.longestPalindrome("babad"))
        .isEqualTo("bab");
  }
}