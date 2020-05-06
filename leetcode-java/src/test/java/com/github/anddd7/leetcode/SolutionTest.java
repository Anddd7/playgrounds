package com.github.anddd7.leetcode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

  @Test
  void name() {
    Assertions.assertThat(
        Solution.INSTANCE
            .mincostTickets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15})
    ).isEqualTo(
        true
    );
  }
}
