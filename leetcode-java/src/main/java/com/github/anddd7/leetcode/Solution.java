package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  /**
   * 动态规划 [1 2 2 5 8 ]
   * <p>fi-2 fi-1 fi, 表示0-i的翻译结果种类数</p>
   * <p>1: 只能有一个可能的翻译结果 a -> 0+1=1</p>
   * <p>2 -> 1+1=2</p>
   * <p>2 -> 1+2=3</p>
   * <p>5 -> 2+3=5</p>
   * <p>8: 58不合法, 之前的fi-2不会再叠加 -> (3)+5=5</p>
   */
  public int translateNum(int num) {
    String s = String.valueOf(num);
    // i = 0
    int pre2 = 0;
    int pre1 = 1;
    int total = 1;
    for (int i = 1; i < s.toCharArray().length; i++) {
      pre2 = pre1;
      pre1 = total;
      total = pre1;

      boolean isValid = s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) < '6');
      if (isValid) {
        total += pre2;
      }
    }
    return total;
  }
}
