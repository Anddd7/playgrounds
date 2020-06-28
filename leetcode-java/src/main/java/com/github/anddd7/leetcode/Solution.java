package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public int minSubArrayLen(int s, int[] nums) {
    int start = 0, end = 0;
    int current = 0;
    int minLength = nums.length + 1;

    while (end < nums.length) {
      int total = current + nums[end];
      int length = end + 1 - start;
      if (total >= s) {
        minLength = Math.min(minLength, length);
        current = current - nums[start++];
      } else {
        current = total;
        end++;
      }
    }

    return minLength > nums.length ? 0 : minLength;
  }
}
