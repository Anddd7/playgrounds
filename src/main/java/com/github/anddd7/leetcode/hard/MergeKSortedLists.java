package com.github.anddd7.leetcode.hard;

import com.github.anddd7.leetcode.HighPerformance;
import com.github.anddd7.leetcode.LowPerformance;
import com.github.anddd7.leetcode.medium.RemoveNthFromEnd.ListNode;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class MergeKSortedLists {

  public ListNode mergeKLists(ListNode[] lists) {
//    return mergeKListsWithInsert(lists);
    return mergeKListsWithPartion(lists);
  }

  /**
   * 依次遍历多个chain, 找出最小的头部节点, 插入到第一个chain中 (类似merge2List)
   * 虽然每一个链只遍历一次, 但是比较操作进行了k次(总的节点数), 等效于一个归并操作
   * 但效率比两路归并低
   */
  @LowPerformance("18%")
  public ListNode mergeKListsWithInsert(ListNode[] lists) {
    if (lists.length == 0) {
      return null;
    }
    if (lists.length == 1) {
      return lists[0];
    }

    ListNode root;
    ListNode previous = null, current;
    current = root = lists[0];

    List<ListNode> waitingList = new ArrayList<>();
    for (int i = 1; i < lists.length; i++) {
      ListNode node = lists[i];
      if (node != null) {
        waitingList.add(lists[i]);
      }
    }

    while (current != null || !waitingList.isEmpty()) {
      int indexOfMinOfWaitToAdd = -1;
      ListNode minOfWaitToAdd = current;
      for (int i = 0; i < waitingList.size(); i++) {
        ListNode node = waitingList.get(i);
        if (minOfWaitToAdd == null || node.val < minOfWaitToAdd.val) {
          minOfWaitToAdd = node;
          indexOfMinOfWaitToAdd = i;
        }
      }

      if (indexOfMinOfWaitToAdd == -1) {
        previous = current;
        current = current.next;
      } else {
        if (minOfWaitToAdd.next == null) {
          waitingList.remove(indexOfMinOfWaitToAdd);
        } else {
          waitingList.set(indexOfMinOfWaitToAdd, minOfWaitToAdd.next);
        }

        if (previous == null) {
          minOfWaitToAdd.next = root;
          previous = root = minOfWaitToAdd;
        } else {
          previous.next = minOfWaitToAdd;
          minOfWaitToAdd.next = current;

          previous = minOfWaitToAdd;
        }
      }
    }
    return root;
  }

  /**
   * 真归并
   */
  @HighPerformance("99%, ")
  public ListNode mergeKListsWithPartion(ListNode[] lists) {
    return partion(lists, 0, lists.length - 1);
  }

  // 两路归并
  public static ListNode partion(ListNode[] lists, int s, int e) {
    if (s == e) {
      return lists[s];
    }
    if (s < e) {
      int q = (s + e) / 2;
      ListNode l1 = partion(lists, s, q);
      ListNode l2 = partion(lists, q + 1, e);
      return merge(l1, l2);
    } else {
      return null;
    }
  }

  // This function is from Merge Two Sorted Lists.
  public static ListNode merge(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    if (l1.val < l2.val) {
      l1.next = merge(l1.next, l2);
      return l1;
    } else {
      l2.next = merge(l1, l2.next);
      return l2;
    }
  }
}
