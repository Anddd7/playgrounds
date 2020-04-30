package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public boolean isHappy(int n) {
    int fast = n;
    int slow = n;
    while (fast != 1) {
      slow = nextNumber(slow);
      fast = nextNumber(nextNumber(fast));

      if (slow == fast && fast != 1) {
        return false;
      }
    }

    return true;
  }

  private int nextNumber(int n) {
    int sum = 0;
    while (n > 0) {
      int d = n % 10;
      n = n / 10;
      sum += d * d;
    }
    return sum;
  }
}
