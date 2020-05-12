package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();
}

class MinStack {

  class Node {


    int val;
    Node next;

    public Node(int val, Node next) {
      this.val = val;
      this.next = next;
    }
  }

  Node minHead;
  Node head;

  /**
   * initialize your data structure here.
   */
  public MinStack() {

  }

  public void push(int x) {
    head = new Node(x, head);
    int min = minHead == null ? x : Math.min(x, minHead.val);
    minHead = new Node(min, minHead);
  }

  public void pop() {
    head = head.next;
    minHead = minHead.next;
  }

  public int top() {
    return head.val;
  }

  public int getMin() {
    return minHead.val;
  }
}
