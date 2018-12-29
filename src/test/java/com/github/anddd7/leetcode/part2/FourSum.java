package com.github.anddd7.leetcode.part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 * @author and777
 * @date 2018/1/2
 *
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d =
 * target? Find all unique quadruplets in the array which gives the sum of target.
 *
 * 序列中找4个数字 a+b+c+d=target
 */
public class FourSum {

  /**
   * 转化为3问题Sum , a+b+c+d=target a+b+c=target-d
   *
   * @param nums 序列
   * @param target 目标值
   * @return 可能的组合
   */
  public List<List<Integer>> fourSum(int[] nums, int target) {
    if (nums.length < 4) {
      return Collections.emptyList();
    }

    List<List<Integer>> result = new ArrayList<>();

    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 3; i++) {
      if (nums[i] > target) {
        break;//提前退出
      }

      if (i > 0 && nums[i] == nums[i - 1]) {
//        System.out.println("fourSum:跳过" + i + "处的重复数字:" + nums[i]);
        continue;
      }

      int[] _nums = Arrays.copyOfRange(nums, i + 1, nums.length);
      int _target = target - nums[i];
      for (List<Integer> threeRecord : threeSum(_nums, _target)) {
//        System.out.println("fourSum:"+threeRecord.toString());
        List<Integer> temp = new ArrayList<>();
        temp.add(nums[i]);
        temp.addAll(threeRecord);
        result.add(temp);
      }
    }

    return result;
  }

  public List<List<Integer>> threeSum(int[] nums, int target) {
    List<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < nums.length - 2; i++) {
      if (nums[i] > target) {
        break;//提前退出
      }
      if (i > 0 && nums[i] == nums[i - 1]) {
//        System.out.println("threeSum:跳过" + i + "处的重复数字:" + nums[i]);
        continue;
      }

      int[] _nums = Arrays.copyOfRange(nums, i + 1, nums.length);
      int _target = target - nums[i];
      for (List<Integer> twoRecord : twoSum(_nums, _target)) {
//        System.out.println("threeSum:"+twoRecord.toString());

        List<Integer> temp = new ArrayList<>();
        temp.add(nums[i]);
        temp.addAll(twoRecord);
        result.add(temp);
      }
    }

    return result;
  }


  public List<List<Integer>> twoSum(int[] nums, int target) {
    List<List<Integer>> result = new ArrayList<>();

    if (nums.length <= 2) {
      if (nums.length == 2 && nums[0] + nums[1] == target) {
        result.add(Arrays.asList(nums[0], nums[1]));
      }
      return result;
    } else if (nums[0] + nums[1] > target) {
//      System.out.println("twoSum:提前退出" + nums[0] + " " + nums[1] + " " + target);
      return result;

    } else if (nums[nums.length - 1] + nums[nums.length - 2] < target) {
//      System.out.println(
//          "twoSum:提前退出" + nums[nums.length - 2] + " " + nums[nums.length - 1] + " " + target);
      return result;
    }

    int start = 0;
    int end = nums.length - 1;

    while (start < end) {
      int sum = nums[start] + nums[end];

      if (sum > target) {
        while (start < end && nums[end] == nums[end - 1]) {
//          System.out.println("twoSum:跳过right-" + end + "处的重复数字:" + nums[end]);
          end--;
        }
        end--;
      } else if (sum < target) {
        while (start < end && nums[start] == nums[start + 1]) {
//          System.out.println("twoSum:跳过left-" + start + "处的重复数字:" + nums[start]);
          start++;
        }
        start++;
      } else {
        result.add(Arrays.asList(nums[start], nums[end]));
        start++;
      }
    }
    return result;
  }

  @Test
  public void test1() {
    System.out.println(fourSum(new int[]{5, 5, 3, 5, 1, -5, 1, -2}, 4).toString());
  }

  @Test
  public void test2() {
    System.out.println(fourSum(
        new int[]{-495, -477, -464, -424, -411, -409, -363, -337, -328, -328, -325, -320, -310,
            -285, -278, -235, -208, -151, -149, -147, -144, -132, -115, -107, -101, -98, -83, -58,
            -58, -56, -51, -46, -45, -7, 0, 4, 4, 21, 51, 52, 57, 60, 104, 109, 124, 141, 158, 205,
            206, 241, 278, 278, 291, 314, 379, 416, 437, 447, 452, 496}
        , -1371).toString());
  }

  @Test
  public void test3() {
    System.out.println(fourSum(
        new int[]{-3,-2,-1,0,0,1,2,3}
        , 0).toString());
  }
}