package com.github.anddd7.leetcode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

  @Test
  void name() {
    Assertions.assertThat(
        Solution.INSTANCE
            .superPow(2147483647, new int[]{2, 0, 0})
    ).isEqualTo(
        1198
    );
  }
}
