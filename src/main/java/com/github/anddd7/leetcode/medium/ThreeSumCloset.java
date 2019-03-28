package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.HighPerformance;
import java.util.Arrays;

@SuppressWarnings("Duplicates")
class ThreeSumCloset {

  @HighPerformance("91%")
  int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int result = nums[0] + nums[1] + nums[2];

    for (int i = 0; i + 2 < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      int j = i + 1, k = nums.length - 1;
      while (j < k) {
        int sum = nums[j] + nums[k] + nums[i];
        if (sum == target) {
          return target;
        }
        if (Math.abs(target - sum) < Math.abs(target - result)) {
          result = sum;
        }

        if (sum > target) {
          k--;
        } else {
          j++;
        }
      }
    }
    return result;
  }
}
