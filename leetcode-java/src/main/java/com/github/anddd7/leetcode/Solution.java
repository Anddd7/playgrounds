package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  /**
   * 翻转一般的数字, 比较
   * 121-> 12 vs 1 -> 1 vs 12
   * 1221 -> 122 vs 1 -> 12 vs 12
   */
  public boolean isPalindrome(int x) {
    if (x < 0 || (x % 10 == 0 && x != 0)) {
      return false;
    }
    if (x < 10) {
      return true;
    }

    int reverse = 0;
    while (x > reverse) {
      reverse = reverse * 10 + x % 10;
      x = x / 10;
    }
    return x == reverse || x == reverse / 10;
  }
}
