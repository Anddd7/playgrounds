package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  /**
   * a^n = a^(10x+y) = a^10x * a^y
   * <p>将n分隔为多位数
   * a * b % x = (a % x) * (b % x) % x
   * <p>逐步取模, 防止溢出
   */
  public int superPow(int a, int[] b) {
    return superPow(a % 1337, b, b.length - 1);
  }

  private int superPow(int a, int[] b, int index) {
    if (index < 0) {
      return 1;
    }

    int mod = b[index];
    int current = myPow(a, mod);
    int previous = myPow(superPow(a, b, index - 1), 10);

    return (current * previous) % 1337;
  }

  private int myPow(int a, int b) {
    int result = 1;
    for (int i = 0; i < b; i++) {
      result *= a;
      result %= 1337;
    }
    return result;
  }
}
