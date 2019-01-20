package com.github.anddd7.leetcode.easy;

import com.github.anddd7.leetcode.HighPerformance;
import com.github.anddd7.leetcode.LowPerformance;
import java.util.HashMap;
import java.util.Map;

/**
 * 在给定数组中存在2个元素, 相加等于target
 * - 排序, 从两端向中间靠拢查询: 取决于排序算法复杂度
 * - 循环一次, 通过HashMap快速确定值: O(n) | O(n)
 * - 双层循环: O(n2) | O(1)
 */
public class TwoSum {

  public int[] twoSum(int[] nums, int target) {
//    return checkEachCombination(nums, target);
    return checkWithHashMap(nums, target);
  }

  @HighPerformance("O[n]")
  private int[] checkWithHashMap(int[] nums, int target) {
    if (nums.length == 2) {
      return new int[]{0, 1};
    }

    Map<Integer, Integer> resultToIndex = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      Integer indexOfAddend = resultToIndex.get(target - nums[i]);
      if (indexOfAddend != null) {
        return new int[]{indexOfAddend, i};
      }
      resultToIndex.put(nums[i], i);
    }
    return new int[0];
  }

  @LowPerformance("O(n^2)")
  private int[] checkEachCombination(int[] nums, int target) {
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
