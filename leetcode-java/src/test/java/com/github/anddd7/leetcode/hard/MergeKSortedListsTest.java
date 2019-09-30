package com.github.anddd7.leetcode.hard;

import static com.github.anddd7.leetcode.medium.RemoveNthFromEndTest.data;
import static com.github.anddd7.leetcode.medium.RemoveNthFromEndTest.string;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.anddd7.leetcode.medium.RemoveNthFromEnd.ListNode;
import org.junit.jupiter.api.Test;

class MergeKSortedListsTest {

  private MergeKSortedLists instance = new MergeKSortedLists();

  @Test
  void case1() {
    assertThat(
        string(
            instance.mergeKLists(new ListNode[]{data("2"), data(""), data("-1")})
        )
    ).isEqualTo("-1->2");
  }

  @Test
  void case2() {
    assertThat(
        string(
            instance.mergeKLists(new ListNode[]{data("1->4->5"), data("1->3->4"), data("2->6")})
        )
    ).isEqualTo("1->1->2->3->4->4->5->6");
  }

  @Test
  void case3() {
    assertThat(
        string(
            instance.mergeKLists(new ListNode[]{data("-2->-1->-1->-1"), data("")})
        )
    ).isEqualTo("-2->-1->-1->-1");
  }
}