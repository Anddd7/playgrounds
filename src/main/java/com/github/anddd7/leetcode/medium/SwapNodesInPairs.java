package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.HighPerformance;
import com.github.anddd7.leetcode.medium.RemoveNthFromEnd.ListNode;

public class SwapNodesInPairs {

  @HighPerformance("100%")
  public ListNode swapPairs(ListNode head) {
    return swapHead(head);
  }


  private static ListNode swapHead(ListNode head) {
    if (head == null) {
      return null;
    }
    if (head.next == null) {
      return head;
    }

    ListNode newHead = head.next;
    ListNode next = newHead.next;

    head.next = swapHead(next);
    newHead.next = head;
    return newHead;
  }
}
