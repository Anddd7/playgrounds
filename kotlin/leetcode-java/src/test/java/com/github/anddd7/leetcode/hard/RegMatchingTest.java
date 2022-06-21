package com.github.anddd7.leetcode.hard;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class RegMatchingTest {

  private RegMatching instance = new RegMatching();

  @Test
  void case1() {
    assertThat(instance.isMatch("aa", "a*")).isTrue();
  }

  @Test
  void case2() {
    assertThat(instance.isMatch("aab", "c*a*b")).isTrue();
  }

  @Test
  void case3() {
    assertThat(instance.isMatch("aaa", "a*a")).isTrue();
  }

  @Test
  void case4() {
    assertThat(instance.isMatch("aaa", "a*b*c")).isFalse();
  }
}