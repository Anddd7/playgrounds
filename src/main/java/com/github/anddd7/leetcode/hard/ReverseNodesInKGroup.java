package com.github.anddd7.leetcode.hard;

import com.github.anddd7.leetcode.HighPerformance;
import com.github.anddd7.leetcode.medium.RemoveNthFromEnd.ListNode;
import java.util.ArrayDeque;
import java.util.Deque;

class ReverseNodesInKGroup {

  @HighPerformance("97% and 95% memory")
  public ListNode reverseKGroup(ListNode head, int k) {
    Deque<ListNode> deque = new ArrayDeque<>(k);
    ListNode current = head;
    int count = k;

    while (count > 0 && current != null) {
      deque.offer(current);
      current = current.next;
      count--;
    }

    if (count > 0) {
      deque.clear();
      return head;
    }

    head = reverseKGroup(current, k);
    while (!deque.isEmpty()) {
      ListNode node = deque.poll();
      node.next = head;
      head = node;
    }
    return head;
  }
}
