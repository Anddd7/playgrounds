package com.github.anddd7.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      // 跳过重复
      if (i != 0 && nums[i - 1] == nums[i]) {
        continue;
      }
      result.addAll(twoSum(nums, i + 1, -nums[i], nums[i]));
    }
    return result;
  }

  private List<List<Integer>> twoSum(int[] nums, int start, int expect, int number) {
    List<List<Integer>> result = new ArrayList<>();
    int s = start, e = nums.length - 1;
    while (s < e) {
      int value = nums[s] + nums[e];
      if (value < expect) {
        s++;
      } else if (value > expect) {
        e--;
      } else {
        result.add(Arrays.asList(number, nums[s], nums[e]));
        // 跳过重复
        while (s < e && nums[s] == nums[s + 1]) {
          s++;
        }
        // 寻找下一个解
        s++;
        // 跳过重复
        while (s < e && nums[e] == nums[e - 1]) {
          e--;
        }
        // 寻找下一个解
        e--;
      }
    }
    return result;
  }
}
