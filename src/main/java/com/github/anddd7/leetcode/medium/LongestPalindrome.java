package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.NormalPerformance;

/**
 * 最长回文串
 */
public class LongestPalindrome {


  @NormalPerformance
  public String longestPalindrome(String s) {
    if (s.length() < 2) {
      return s;
    }

    String result = "";
    for (int i = 0; i < s.length() - 1; i++) {
      // 从i向两边找
      String s1 = extend(s, i, i);
      // 从i,i+1向两边找
      String s2 = extend(s, i, i + 1);

      if (s1.length() > result.length()) {
        result = s1;
      }
      if (s2.length() > result.length()) {
        result = s2;
      }
    }
    return result;
  }

  private String extend(String s, int i, int j) {
    while (0 <= i && j < s.length() && s.charAt(i) == s.charAt(j)) {
      i--;
      j++;
    }
    return i + 1 < j ? s.substring(i + 1, j) : "";
  }
}
