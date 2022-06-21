package com.github.anddd7.leetcode.easy;

import com.github.anddd7.leetcode.HighPerformance;

public class StrStr {

  @HighPerformance("90%")
  public int strStr(String haystack, String needle) {
    if (haystack.length() < needle.length()) {
      return -1;
    }
    if (needle.length() == 0) {
      return 0;
    }

    int index = 0, matched = 0;
    while (index <= haystack.length() - needle.length() + matched) {

      if (haystack.charAt(index) == needle.charAt(matched)) {
        index++;
        matched++;
        if (matched == needle.length()) {
          return index - matched;
        }
      } else {
        index = index - matched + 1;
        matched = 0;
      }
    }

    return -1;
  }
}
