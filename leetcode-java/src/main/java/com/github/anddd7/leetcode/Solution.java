package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public int reverseBits(int num) {
    if (num == 0) {
      return 1;
    }

    int pre = 0, cur = 0;
    int max = 0;

    while (num > 0) {
      if ((1 & num) == 1) {
        cur++;
      } else {
        max = Math.max(max, pre + cur + 1);
        pre = cur;
        cur = 0;
      }
      num >>= 1;
    }
    return Math.max(max, pre + cur + 1);
  }
}
