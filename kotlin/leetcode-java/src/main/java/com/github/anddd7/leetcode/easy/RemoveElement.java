package com.github.anddd7.leetcode.easy;

import com.github.anddd7.leetcode.HighPerformance;

public class RemoveElement {

  @HighPerformance("100% with 96% memory")
  public int removeElement(int[] nums, int val) {
    int i = 0;
    int length = nums.length;
    while (i < length) {
      if (nums[i] == val) {
        int j = i + 1;
        while (j < length && nums[j] == val) {
          j++;
        }
        // last one is target, ignore directly
        if (j == length) {
          return length - j + i;
        }
        System.arraycopy(nums, j, nums, i, length - j);
        length -= j - i;
      }
      i++;
    }
    return length;
  }
}
