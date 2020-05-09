package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public int mySqrt(int x) {
    int start = 0, end = x;
    while (start <= end) {
      int mid = (start + end) / 2;
      long value = (long) mid * mid;
      if (value == x) {
        return mid;
      }
      if (value < x) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return end;
  }

//  public int mySqrt(int x) {
//    return findMySqrt(x, 0, x);
//  }

  private int findMySqrt(int x, long start, long end) {
    if (start >= end) {
      return (int) start;
    }

    long mid = (start + end + 1) / 2;
    long value = mid * mid;
    if (value <= x) {
      return findMySqrt(x, mid, end);
    }
    return findMySqrt(x, start, mid - 1);
  }
}
