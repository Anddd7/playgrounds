package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.HighPerformance;
import com.github.anddd7.leetcode.medium.RemoveNthFromEnd.ListNode;

/**
 * 给定两个链表(不等长), 对值做加法, 包含进位
 */
public class AddTwoNumbers {

  @HighPerformance
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    return addNumber(0, l1, l2);
  }

  private ListNode addNumber(int carry, ListNode l, ListNode r) {
    if (l == null && r == null) {
      return carry > 0 ? new ListNode(carry) : null;
    }

    ListNode l1 = l != null ? l : r;
    ListNode r1 = l != null && r != null ? r : new ListNode(0);

    int total = carry + l1.val + r1.val;
    int newValue = total < 10 ? total : total - 10;
    int newCarry = total < 10 ? 0 : 1;

    l1.val = newValue;
    l1.next = addNumber(newCarry, l1.next, r1.next);
    return l1;
  }
}
