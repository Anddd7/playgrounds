package com.github.anddd7.leetcode.easy;

import com.github.anddd7.leetcode.HighPerformance;

class RomanToInteger {

  @HighPerformance("99.95%, but memory is a little bit high (use a single method?)")
  int romanToInt(String s) {
    int result = 0;
    int previous = 0;
    char[] chars = s.toCharArray();
    for (int i = chars.length - 1; i >= 0; i--) {
      int current = getNumberOfRomanChar(chars[i]);
      if (current < previous) {
        result -= current;
      } else {
        result += current;
      }
      previous = current;
    }
    return result;
  }

  private int getNumberOfRomanChar(char c) {
    switch (c) {
      case 'M':
        return 1000;
      case 'D':
        return 500;
      case 'C':
        return 100;
      case 'L':
        return 50;
      case 'X':
        return 10;
      case 'V':
        return 5;
      default:
        return 1;
    }
  }
}