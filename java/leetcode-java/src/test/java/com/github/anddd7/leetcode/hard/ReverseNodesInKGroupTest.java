package com.github.anddd7.leetcode.hard;

import static com.github.anddd7.leetcode.medium.RemoveNthFromEndTest.data;
import static com.github.anddd7.leetcode.medium.RemoveNthFromEndTest.string;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ReverseNodesInKGroupTest {

  private ReverseNodesInKGroup instance = new ReverseNodesInKGroup();

  @Test
  void case1() {
    Assertions.assertThat(
        string(
            instance.reverseKGroup(data("1->2->3->4->5"), 2)
        )
    ).isEqualTo("2->1->4->3->5");
  }

  @Test
  void case2() {
    Assertions.assertThat(
        string(
            instance.reverseKGroup(data("1->2->3->4->5"), 3)
        )
    ).isEqualTo("3->2->1->4->5");
  }
}