package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public boolean validPalindrome(String s) {
    return isPalindrome(s, 0, s.length() - 1, true);
  }

  public boolean isPalindrome(String s, int i, int j, boolean allowCut) {
    while (i < j) {
      if (s.charAt(i) == s.charAt(j)) {
        i++;
        j--;
      } else {
        return allowCut && (
            isPalindrome(s, i + 1, j, false) || isPalindrome(s, i, j - 1, false)
        );
      }
    }
    return true;
  }
}
