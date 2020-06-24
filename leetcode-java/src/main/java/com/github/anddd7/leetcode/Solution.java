package com.github.anddd7.leetcode;

import java.util.Arrays;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public int threeSumClosest(int[] nums, int target) {
    int result = nums[0] + nums[1] + nums[2];
    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 2; i++) {
      int a = nums[i];
      if (i != 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      int s = i + 1, e = nums.length - 1;
      while (s < e) {
        int b = nums[s];
        int c = nums[e];
        int value = a + b + c;

        if (value == target) {
          return value;
        }

        if (value < target) {
          s++;
        } else {
          e--;
        }

        if (Math.abs(value - target) < Math.abs(result - target)) {
          result = value;
        }
      }
    }

    return result;
  }
}
