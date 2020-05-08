package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public int rob(int[] nums) {
    int total = 0;
    for (int i = 0; i < nums.length; i++) {
      int fromCurrent = i < 2 ? nums[i] : nums[i] + nums[i - 2];
      int fromPrevious = i < 1 ? 0 : nums[i - 1];
      nums[i] = Math.max(fromCurrent, fromPrevious);
      total = Math.max(total, nums[i]);
    }
    return total;
  }
}
