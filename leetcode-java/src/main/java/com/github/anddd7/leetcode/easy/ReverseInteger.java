package com.github.anddd7.leetcode.easy;

import com.github.anddd7.leetcode.NormalPerformance;

public class ReverseInteger {

  @NormalPerformance("84%")
  public int reverse(int x) {
    if (-10 < x && x < 10) {
      return x;
    }

    String s = Integer.toString(x);
    boolean isNegative = s.charAt(0) == '-';

    StringBuilder sb = new StringBuilder();
    for (int i = s.length() - 1; (isNegative ? 0 : -1) < i; i--) {
      sb.append(s.charAt(i));
    }
    String result = (isNegative ? "-" : "") + sb.toString();

    try {
      return Integer.parseInt(result);
    } catch (NumberFormatException e) {
      return 0;
    }
  }
}
