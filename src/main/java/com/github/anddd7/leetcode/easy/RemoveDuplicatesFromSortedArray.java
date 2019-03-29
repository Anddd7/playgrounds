package com.github.anddd7.leetcode.easy;

import com.github.anddd7.leetcode.HighPerformance;

public class RemoveDuplicatesFromSortedArray {

  @HighPerformance("100% and 92% memory")
  public int removeDuplicates(int[] nums) {
    if (nums.length < 2) {
      return nums.length;
    }

    int index = 0;
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
        i++;
      }
      nums[index++] = num;
    }
    return index;
  }
}
