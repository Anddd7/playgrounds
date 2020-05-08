package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }

    ListNode(int x, ListNode next) {
      val = x;
      this.next = next;
    }
  }


  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null) {
      return null;
    }
    ListNode current = head;
    for (int i = 0; i < k; i++) {
      if (current == null) {
        return head;
      }
      current = current.next;
    }

    ListNode follower = reverseKGroup(current, k);

    current = head;
    while (k-- > 0) {
      ListNode next = current.next;
      current.next = follower;
      follower = current;
      current = next;
    }
    return follower;
  }
}
