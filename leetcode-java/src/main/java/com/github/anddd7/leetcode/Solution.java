package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) {
      return "";
    }
    if (strs.length == 1) {
      return strs[0];
    }
    String min = strs[0];
    for (String str : strs) {
      if (str.length() == 0) {
        return "";
      }
      if (str.length() < min.length()) {
        min = str;
      }
    }

    int index = 0;
    while (index < min.length()) {
      char current = min.charAt(index);
      for (String str : strs) {
        if (str.charAt(index) != current) {
          return min.substring(0, index);
        }
      }
      index++;
    }
    return min;
  }
}
