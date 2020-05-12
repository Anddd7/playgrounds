package com.github.anddd7.leetcode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

  @Test
  void name() {
    Assertions.assertThat(
        Solution.INSTANCE.regionsBySlashes(new String[]{" /", "  "})
    ).isEqualTo(
        46340
    );
  }
}
