package com.github.anddd7.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public TreeNode recoverFromPreorder(String S) {
    Deque<TreeNode> stack = new LinkedList<>();
    int i = 0;
    while (i < S.length()) {
      int deep = 0;
      while (S.charAt(i) == '-') {
        ++deep;
        ++i;
      }
      int value = 0;
      while (i < S.length() && Character.isDigit(S.charAt(i))) {
        value = value * 10 + (S.charAt(i) - '0');
        ++i;
      }
      TreeNode node = new TreeNode(value);
      if (deep == stack.size()) {
        if (!stack.isEmpty()) {
          stack.peek().left = node;
        }
      } else {
        while (deep != stack.size()) {
          stack.pop();
        }
        stack.peek().right = node;
      }
      stack.push(node);
    }
    return stack.peekLast();
  }
}
