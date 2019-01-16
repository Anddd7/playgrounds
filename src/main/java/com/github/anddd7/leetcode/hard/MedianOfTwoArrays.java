package com.github.anddd7.leetcode.hard;

import com.github.anddd7.leetcode.HighPerformance;
import com.github.anddd7.leetcode.LowPerformance;

/**
 * 找2个数组的中位数
 */
public class MedianOfTwoArrays {

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//    return mergeSorting(nums1, nums2);
    return binarySearch(nums1, nums2);
  }

  @LowPerformance("O[(m+n)/2]")
  private double mergeSorting(int[] nums1, int[] nums2) {
    int total = nums1.length + nums2.length;
    int median = (total) >> 1;
    boolean isOdd = median << 1 != (total);

    int i = 0;
    int j = 0;
    int[] merge = new int[total];
    while (i + j <= median) {
      if (i == nums1.length) {
        merge[i + j] = nums2[j++];
      } else if (j == nums2.length) {
        merge[i + j] = nums1[i++];
      } else if (nums1[i] < nums2[j]) {
        merge[i + j] = nums1[i++];
      } else if (nums1[i] > nums2[j]) {
        merge[i + j] = nums2[j++];
      } else {
        merge[i + j] = nums1[i++];
        merge[i + j] = nums2[j++];
      }
    }

    return isOdd ? merge[median] : (merge[median - 1] + merge[median]) / 2.0;
  }

  @HighPerformance("binary search + find kth")
  private double binarySearch(int[] nums1, int[] nums2) {
    int total = nums1.length + nums2.length;
    int median1 = (total + 1) / 2;
    int median2 = (total + 2) / 2;

    if (median1 == median2) {
      return findKth(nums1, 0, nums2, 0, median1);
    } else {
      return (findKth(nums1, 0, nums2, 0, median1) + findKth(nums1, 0, nums2, 0, median2)) / 2.0;
    }
  }

  private double findKth(int[] A, int i1, int[] B, int i2, int k) {
    if (i1 > A.length - 1) {
      // A is empty, find kth in B
      return B[i2 + k - 1];
    }
    if (i2 > B.length - 1) {
      return A[i1 + k - 1];
    }
    if (k == 1) {
      return Math.min(A[i1], B[i2]);
    }

    int midA = Integer.MAX_VALUE;
    int midB = Integer.MAX_VALUE;

    // try to cut A -> [i1,i1+k/2-1] [i1+k/2,~]
    int midIndexA = i1 + k / 2 - 1;
    if (midIndexA < A.length) {
      // get max number of left part of A
      midA = A[midIndexA];
    }

    // cut B with same size
    int midIndexB = i2 + k / 2 - 1;
    if (midIndexB < B.length) {
      midB = B[midIndexB];
    }

    // if leftA.max < leftB.max, search rightA + leftB
    // if B is shorter than k/2 (midIndexB=MAX_VALUE), search rightA + B
    if (midA < midB) {
      return findKth(A, i1 + k / 2, B, i2, k - k / 2);
    } else {
      return findKth(A, i1, B, i2 + k / 2, k - k / 2);
    }
  }
}
