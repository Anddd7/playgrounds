package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.HighPerformance;
import com.github.anddd7.leetcode.LowPerformance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ThreeSum {

  @LowPerformance("48%")
  List<List<Integer>> threeSum(int[] nums) {
//    return threeSumNormal(nums);
    return threeSumSkipAsMuchAsPossible(nums);
  }

  /**
   * 按从小到大对数组进行排序, 选定nums[i]作为第一个加数, 试图在剩下的数组中寻找另两个加数
   * - (排序后)如果nums[i]==nums[k], 组合的结果一定是一样的, 使用Set跳过重复数字
   * - (排序后)如果nums[i]+nums[m]+nums[n]<0, 表示nums[m]+nums[n]太小, 需要其中一个数字变大; 同理>0时, 说明和太大
   * |  - 采用从数组两端(最大+最小)向中间进行遍历
   * |  - (可优化) 短路退出
   */
  @LowPerformance("48%")
  List<List<Integer>> threeSumNormal(int[] nums) {
    Arrays.sort(nums);

    Set<Integer> resolved = new HashSet<>();
    Set<List<Integer>> added = new HashSet<>();

    for (int i = 0; i < nums.length - 2; i++) {
      final int current = nums[i];
      if (resolved.contains(current)) {
        continue;
      }

      int start = i + 1;
      int end = nums.length - 1;

      int max = nums[end] + nums[end - 1];
      int min = nums[start] + nums[start + 1];
      if (current + max < 0 || current + min > 0) {
        continue;
      }

      while (start < end) {
        int sum = nums[start] + nums[end] + current;
        if (sum > 0) {
          end--;
        } else if (sum < 0) {
          start++;
        } else {
          List<Integer> result = new ArrayList<>();
          result.add(nums[start]);
          result.add(nums[end]);
          result.add(current);
          Collections.sort(result);

          added.add(result);

          start++;
          end--;
        }
      }
      resolved.add(current);
    }
    return new ArrayList<>(added);
  }

  /**
   * - 通过比较前一个resolved, 避免使用Set -> 减少耗时/内存
   * - 在遍历查询后两个元素时, 也通过比较进行skip, 避免使用Set/构造重复的结果数组 -> 减少耗时/内存
   */
  @HighPerformance("96%, also low memory used > 99.9%")
  private List<List<Integer>> threeSumSkipAsMuchAsPossible(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i + 2 < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      int j = i + 1, k = nums.length - 1;
      int target = -nums[i];
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
