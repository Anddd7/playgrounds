package com.github.anddd7.leetcode.easy;

import com.github.anddd7.leetcode.HighPerformance;

public class PalindromeNumber {

  @HighPerformance("O[n], 94%")
  public boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    }
    if (x < 10) {
      return true;
    }

    String s = String.valueOf(x);

    for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
    }

    return true;
  }
}
