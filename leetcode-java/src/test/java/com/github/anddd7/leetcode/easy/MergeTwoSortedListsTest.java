package com.github.anddd7.leetcode.easy;

import static com.github.anddd7.leetcode.medium.RemoveNthFromEndTest.data;
import static com.github.anddd7.leetcode.medium.RemoveNthFromEndTest.string;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MergeTwoSortedListsTest {

  private MergeTwoSortedLists instance = new MergeTwoSortedLists();

  @Test
  void case1() {
    assertThat(
        string(
            instance.mergeTwoLists(data("1->2->4"), data("1->3->4"))
        )
    ).isEqualTo("1->1->2->3->4->4");
  }

  @Test
  void case2() {
    assertThat(
        string(
            instance.mergeTwoLists(data("1->2->4"), data("1->2->3->4->5->6->7"))
        )
    ).isEqualTo("1->1->2->2->3->4->4->5->6->7");
  }
}