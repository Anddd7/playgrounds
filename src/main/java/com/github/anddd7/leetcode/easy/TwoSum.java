package com.github.anddd7.leetcode.easy;

import com.github.anddd7.leetcode.LowPerformance;

/**
 * 在给定数组中存在2个元素, 相加等于target
 * - 排序, 从两端向中间靠拢查询: 取决于排序算法复杂度
 * - 循环一次, 通过HashMap快速确定值: O(n) | O(n)
 * - 双层循环: O(n2) | O(1)
 */
public class TwoSum {

  @LowPerformance
  public int[] twoSum(int[] nums, int target) {
    if (nums.length == 2) {
      return new int[]{0, 1};
    }

    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          return new int[]{i, j};
        }
      }
    }
    return new int[0];
  }
}
