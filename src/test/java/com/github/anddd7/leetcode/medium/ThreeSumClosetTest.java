package com.github.anddd7.leetcode.medium;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ThreeSumClosetTest {

  private ThreeSumCloset instance = new ThreeSumCloset();

  @Test
  void case1() {
    assertThat(instance.threeSumClosest(new int[]{-1, 2, 1, -4}, 1)).isEqualTo(2);
  }

  @Test
  void case2() {
    assertThat(instance.threeSumClosest(new int[]{-3, -2, -5, 3, -4}, -1)).isEqualTo(-2);
  }

  @Test
  void case3() {
    assertThat(instance.threeSumClosest(new int[]{-1, 0, 1, 1, 55}, 3)).isEqualTo(2);
  }
}