package com.github.anddd7.leetcode;

import java.util.Random;

public class Solution {

  public static final Solution INSTANCE = new Solution(new int[]{1, 3});


  /**
   * 前缀和: 想象w[i]是一根线段, 前缀和就是把它们连接起来的一条更长的线段 当在线段上随机取一个点(数字), 它落在w[i]的几率和w[i]的长度成正比
   */
  private final int[] psum;
  private final Random r = new Random();

  public Solution(int[] w) {
    psum = new int[w.length];
    int tot = 0;
    for (int i = 0; i < w.length; i++) {
      tot += w[i];
      psum[i] = tot;
    }
  }

  public int pickIndex() {
    int target = r.nextInt(psum[psum.length - 1]);
    return find(psum, 0, psum.length - 1, target);
  }

  /**
   * 二分法找最小的index, 使target<psum[index]
   */
  private int find(int[] psum, int s, int e, int target) {
    if (s == e) {
      return s;
    }

    int mid = (s + e) / 2;
    int value = psum[mid];

    if (target < value) {
      return find(psum, s, mid, target);
    }
    if (target > value) {
      return find(psum, mid + 1, e, target);
    }
    return mid + 1;
  }
}
