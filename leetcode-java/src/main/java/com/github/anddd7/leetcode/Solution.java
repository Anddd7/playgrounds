package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  /**
   * f(i) = f(i-1) + f(i-2)[1<i<n]
   * 1 -> 0 1 1 -> 1
   * 2 -> ... -> 1 1 2 -> 2
   * 3 -> ... -> 1 2 3 -> 3
   * 4 -> ... -> 2 3 5 -> 5
   */
  public int climbStairs(int n) {
    int pre2 = 0;
    int pre1 = 1;
    int current = 1;

    for (int i = 1; i < n; i++) {
      pre2 = pre1;
      pre1 = current;
      current = pre2 + pre1;
    }
    return current;
  }
}
