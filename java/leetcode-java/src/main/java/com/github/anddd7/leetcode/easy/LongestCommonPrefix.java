package com.github.anddd7.leetcode.easy;

import com.github.anddd7.leetcode.HighPerformance;

class LongestCommonPrefix {

  @HighPerformance("93.5%")
  String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) {
      return "";
    }
    if (strs.length == 1) {
      return strs[0];
    }

    StringBuilder prefix = new StringBuilder();
    int index = 0;
    while (true) {
      for (String str : strs) {
        if (index >= str.length() || strs[0].charAt(index) != str.charAt(index)) {
          return prefix.toString();
        }
      }
      prefix.append(strs[0].charAt(index));
      index++;
    }
  }
}
