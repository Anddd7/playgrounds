package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.NormalPerformance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FourSum {

  /**
   * 确定第一位, 循环在子数组中找 3Sum
   * - 增加短路退出: (100%)
   * |  - min*4 > target || max*4 < target  : 没有合适的输出
   * |  - nums[i] + max*3 < target          : nums[i]太小
   * |  - nums[i] * 4 > target              : nums[i]太大
   * |  - ...
   */
  @NormalPerformance("65% with 75% memory")
  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);

    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < nums.length - 3; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      List<List<Integer>> subs = threeSum(nums, i + 1, target - nums[i]);
      for (List<Integer> sub : subs) {
        List<Integer> result = new ArrayList<>();
        result.add(nums[i]);
        result.addAll(sub);
        res.add(result);
      }
    }
    return res;
  }

  @SuppressWarnings("Duplicates")
  private List<List<Integer>> threeSum(int[] nums, int index, int _target) {
    List<List<Integer>> res = new ArrayList<>();
    for (int i = index; i < nums.length - 2; i++) {
      if (i > index && nums[i] == nums[i - 1]) {
        continue;
      }
      int j = i + 1, k = nums.length - 1;
      int target = _target - nums[i];
      while (j < k) {
        if (nums[j] + nums[k] == target) {
          res.add(Arrays.asList(nums[i], nums[j], nums[k]));
          j++;
          k--;
          while (j < k && nums[j] == nums[j - 1]) {
            j++;
          }
          while (j < k && nums[k] == nums[k + 1]) {
            k--;
          }
        } else if (nums[j] + nums[k] > target) {
          k--;
        } else {
          j++;
        }
      }
    }
    return res;
  }
}
