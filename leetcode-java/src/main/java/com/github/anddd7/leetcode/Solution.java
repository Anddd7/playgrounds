package com.github.anddd7.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  /**
   * 类似()匹配
   */
  public boolean isValid(String S) {
    Deque<Character> stack = new ArrayDeque<>(S.length());
    for (char c : S.toCharArray()) {
      if (c == 'c') {
        if (stack.size() < 2 || stack.pop() != 'b' || stack.pop() != 'a') {
          return false;
        }
      } else {
        stack.push(c);
      }
    }
    return stack.isEmpty();
  }
}
