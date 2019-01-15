package com.github.anddd7.leetcode.medium;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class LongestSubStrWithoutRepeatTest {

  private LongestSubStrWithoutRepeat instance = new LongestSubStrWithoutRepeat();

  @Test
  void case1() {
    assertThat(instance.lengthOfLongestSubstring("dvdf")).isEqualTo(3);
  }

  @Test
  void case2() {
    assertThat(instance.lengthOfLongestSubstring("abcabcbb")).isEqualTo(3);
  }
}