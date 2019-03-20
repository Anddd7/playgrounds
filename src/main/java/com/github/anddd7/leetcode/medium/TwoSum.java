package com.github.anddd7.leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TwoSum {

  List<List<Integer>> twoSum(int[] nums, int sum) {
    List<List<Integer>> results = new ArrayList<>();
    Set<Integer> store = new HashSet<>();

    for (int current : nums) {
      int target = sum - current;
      if (store.contains(target)) {
        List<Integer> result = new ArrayList<>();
        result.add(current);
        result.add(target);
        results.add(result);
      } else {
        store.add(current);
      }
    }
    return results;
  }
}
