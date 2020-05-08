package com.github.anddd7.leetcode;

import com.github.anddd7.leetcode.Solution.ListNode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

  @Test
  void name() {
    Assertions.assertThat(
        Solution.INSTANCE
            .reverseKGroup(
                new ListNode(1,
                    new ListNode(2,
                        new ListNode(3,
                            new ListNode(4,
                                new ListNode(5)
                            )
                        )
                    )
                ),
                3
            )
    ).isEqualTo(
        4
    );
  }
}
