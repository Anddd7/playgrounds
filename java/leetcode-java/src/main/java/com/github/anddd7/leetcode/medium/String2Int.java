package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.NormalPerformance;

public class String2Int {

  @NormalPerformance("81%")
  public int myAtoi(String str) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if ((c == 32 || c == 43 || c == 45) && result.length() > 0) {
        break;
      }

      if (c == 43 || c == 45 || (48 <= c && c <= 57)) { // + - 0..9
        result.append(c);
      } else if (c != 32) {
        break;
      }
    }

    try {
      return Integer.parseInt(result.toString());
    } catch (NumberFormatException e) {
      return result.length() < 2 ? 0 :
          result.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }
  }
}