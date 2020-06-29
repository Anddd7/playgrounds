package com.github.anddd7.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
    for (int num : nums) {
      if (minHeap.size() < k) {
        minHeap.add(num);
      } else {
        if (num > minHeap.peek()) {
          minHeap.poll();
          minHeap.add(num);
        }
      }
    }
    return minHeap.peek();
  }
}
