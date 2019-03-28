package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.NormalPerformance;

public class RemoveNthFromEnd {

  public static class ListNode {

    public int val;
    public ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  /**
   * 倒序计数:
   * - current坐标为0, 则需要删除的元素坐标为[n-1]
   * - 从head开始遍历, 当遍历元素数量为[x]时, head的坐标刚好为[x-1]
   * |  - 如果x=n, 则head刚好是需要被remove的元素, nth = head
   * |  - 如果x<n, 则需要继续遍历, head的坐标++, 需要被remove元素移动至 nth = head.next
   * |  - 直到current.next=null, 需要被remove的元素就是当前的 nth
   * - remove时需要 previous.next = previous.next.next, 因此记录nth元素无效, 需要记录previousOfNth
   * - 因此所有判断条件变为: 当x+1=n时, 则previousOfNth=head
   * - 如果最后previousOfNth=null(n有效), 表示待删除的是head本身(无previous)
   */
  @NormalPerformance("72% with 67% memory")
  public ListNode removeNthFromEnd(ListNode head, int n) {
    int indexOfHead = 0;
    ListNode current = head;
    ListNode previousOfNth = null;
    while (current != null) {
      indexOfHead++;
      if (indexOfHead + 1 == n) {
        previousOfNth = head;
      } else if (previousOfNth != null) {
        previousOfNth = previousOfNth.next;
      }
      current = current.next;
    }
    if (previousOfNth == null) {
      return head.next;
    }
    previousOfNth.next = previousOfNth.next.next;
    return head;
  }
}
