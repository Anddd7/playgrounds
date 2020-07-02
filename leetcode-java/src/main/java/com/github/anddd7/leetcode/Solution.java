package com.github.anddd7.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public int kthSmallest(int[][] matrix, int k) {
    int length = matrix.length * matrix[0].length;
    if (k < length >> 1) {
      return getKthSmallest(matrix, k);
    } else {
      return getKthLargest(matrix, length - k + 1);
    }
  }

  private int getKthSmallest(int[][] matrix, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>(k, (a, b) -> b - a);
    for (int[] line : matrix) {
      for (int item : line) {
        if (heap.size() == k) {
          if (heap.peek() > item) {
            heap.poll();
          } else {
            break;
          }
        }
        heap.add(item);
      }
    }
    return heap.peek();
  }

  private int getKthLargest(int[][] matrix, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
    for (int i = matrix.length - 1; i >= 0; i--) {
      int[] line = matrix[i];
      for (int j = line.length - 1; j >= 0; j--) {
        int item = line[j];
        if (minHeap.size() == k) {
          if (minHeap.peek() < item) {
            minHeap.poll();
          } else {
            break;
          }
        }
        minHeap.add(item);
      }
    }
    return minHeap.peek();
  }
}
