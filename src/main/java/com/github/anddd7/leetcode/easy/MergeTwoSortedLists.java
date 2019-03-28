package com.github.anddd7.leetcode.easy;

import com.github.anddd7.leetcode.HighPerformance;
import com.github.anddd7.leetcode.medium.RemoveNthFromEnd.ListNode;


@SuppressWarnings("Duplicates")
class MergeTwoSortedLists {

  @HighPerformance("100%!!!!, 97.90% memory")
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }

    ListNode root; // head of result chain
    ListNode previous = null, current;
    ListNode waitToAdd;

    // choose one list as the container
    if (l1.val < l2.val) {
      current = root = l1;
      waitToAdd = l2;
    } else {
      current = root = l2;
      waitToAdd = l1;
    }

    while (current != null && waitToAdd != null) {
      if (current.val > waitToAdd.val) {
        // if `waitToAdd` is less than `current`, it should be inserted before `current`
        ListNode insert = waitToAdd;
        waitToAdd = waitToAdd.next;

        // if `previous` is null (`current` is head of node chain)
        // `insert` as head, then put `current` as the next node
        if (previous == null) {
          insert.next = root;
          previous = root = insert; // now `insert` is head and previous of `current`
        } else {
          // `insert` should be inserted between `previous` and `current`
          previous.next = insert;
          insert.next = current;

          previous = insert;
        }
      } else {
        // if `waitToAdd` is bigger, move next to find a proper position
        previous = current;
        current = current.next;
      }
    }

    // if `waitToAdd` have remaining nodes which are bigger than result[max], just append to result
    if (waitToAdd != null) {
      previous.next = waitToAdd;
    }

    return root;
  }
}