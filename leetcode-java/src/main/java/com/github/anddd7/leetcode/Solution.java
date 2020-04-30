package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  /**
   * p + n = s = i * (i + 1) / 2
   * p - n = t
   * --------------
   * s - t = 2n >= 0
   */
  public int reachNumber(int target) {
    int t = Math.abs(target);
    for (int i = 1; ; i++) {
      int s = i * (i + 1) / 2;
      if (s >= t && (s - t) % 2 == 0) {
        return i;
      }
    }
  }
}
