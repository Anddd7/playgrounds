package com.github.anddd7.leetcode.easy;

import com.github.anddd7.leetcode.HighPerformance;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public class ValidParentheses {

  @HighPerformance("99.5%")
  public boolean isValid(String s) {
    Deque<Character> stack = new LinkedList<>();
    for (char c : s.toCharArray()) {
      if (c == '[' || c == '{' || c == '(') {
        stack.push(c);
      } else if (c == ']') {
        if (Objects.equals(stack.peek(), '[')) {
          stack.pop();
        } else {
          return false;
        }
      } else if (c == '}') {
        if (Objects.equals(stack.peek(), '{')) {
          stack.pop();
        } else {
          return false;
        }
      } else if (c == ')') {
        if (Objects.equals(stack.peek(), '(')) {
          stack.pop();
        } else {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }
}
