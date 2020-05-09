package com.github.anddd7.leetcode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

  @Test
  void name() {
    Assertions.assertThat(
        Solution.INSTANCE
            .mySqrt(2147483647)
    ).isEqualTo(
        46340
    );
  }
}
