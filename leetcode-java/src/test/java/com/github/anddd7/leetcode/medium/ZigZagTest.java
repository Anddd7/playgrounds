package com.github.anddd7.leetcode.medium;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ZigZagTest {

  private ZigZag instance = new ZigZag();

  @Test
  void case1() {
    assertThat(instance.convert("PAYPALISHIRING", 3))
        .isEqualTo("PAHNAPLSIIGYIR");
  }

}