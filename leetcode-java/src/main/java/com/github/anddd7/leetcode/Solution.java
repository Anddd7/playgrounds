package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public boolean carPooling(int[][] trips, int capacity) {
    int[] changes = new int[1001];

    for (int[] trip : trips) {
      int num = trip[0];
      int start = trip[1];
      int end = trip[2];
      changes[start] += num;
      changes[end] -= num;
    }

    for (int passenger : changes) {
      capacity -= passenger;
      if (capacity < 0) {
        return false;
      }
    }

    return true;
  }
}
