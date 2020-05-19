package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public int maxProduct(int[] nums) {
    int max, min, result;
    max = min = result = nums[0];
    for (int i = 1; i < nums.length; i++) {
      int x = max * nums[i];
      int y = min * nums[i];
      max = Math.max(nums[i], Math.max(x, y));
      min = Math.min(nums[i], Math.min(x, y));
      result = Math.max(max, result);
    }
    return result;
  }
}
