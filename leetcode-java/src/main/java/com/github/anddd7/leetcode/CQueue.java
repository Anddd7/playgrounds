package com.github.anddd7.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class CQueue {

  public static final CQueue INSTANCE = new CQueue();

  private final Deque<Integer> stack = new LinkedList<>();
  private final Deque<Integer> reverseStack = new LinkedList<>();

  public CQueue() {

  }

  public void appendTail(int value) {
    stack.push(value);
  }

  public int deleteHead() {
    if (reverseStack.isEmpty()) {
      while (!stack.isEmpty()) {
        reverseStack.push(stack.poll());
      }
    }
    return reverseStack.isEmpty() ? -1 : reverseStack.pop();
  }
}
