package com.github.anddd7.leetcode.part1;

import org.junit.jupiter.api.Test;

public class ReverseInteger {

  public int reverse(int x) {
    int source = Math.abs(x);
    int length = String.valueOf(source).length();
    int result = 0;
    for (int i = 0; i < length; i++) {
      //overflow return 0
      if (Integer.MAX_VALUE / 10 < result || Integer.MAX_VALUE - 10 * result < source % 10) {
        return 0;
      }
      result = 10 * result + source % 10;
      source = source / 10;
    }
    return x < 0 ? -1 * result : result;
  }

  @Test
  public void test() {
    ReverseInteger lab = new ReverseInteger();
    System.out.println(lab.reverse(-1534236469));
  }
}
