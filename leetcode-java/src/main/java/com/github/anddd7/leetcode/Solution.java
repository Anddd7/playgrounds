package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public double myPow(double x, int n) {
    if (x == 0 || x == 1) {
      return x;
    }
    return n < 0 ? 1.0 / quickMul(x, -n) : quickMul(x, n);
  }

  private double quickMul(double x, int n) {
    if (n == 0) {
      return 1;
    }
    double y = quickMul(x, n / 2);
    if (n % 2 == 0) {
      return y * y;
    }
    return x * y * y;
  }
}
