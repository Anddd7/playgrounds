package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public boolean isPalindrome(String s) {
    if (s.isEmpty()) {
      return true;
    }
    int x = 0, y = s.length() - 1;
    while (x < y) {
      int front = s.codePointAt(x);
      int end = s.codePointAt(y);

      if (!Character.isLetterOrDigit(front)) {
        x++;
      } else if (!Character.isLetterOrDigit(end)) {
        y--;
      } else if (Character.toLowerCase(front) == Character.toLowerCase(end)) {
        x++;
        y--;
      } else {
        return false;
      }
    }
    return true;
  }
}
