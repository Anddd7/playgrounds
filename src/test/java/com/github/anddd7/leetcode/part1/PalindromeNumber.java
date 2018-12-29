package com.github.anddd7.leetcode.part1;

import org.junit.jupiter.api.Test;

public class PalindromeNumber {

  public boolean isPalindrome(int x) {
    if (x < 10) {
      return x > -1;
    }

    if (x % 10 == 0) {
      return false;
    }

    boolean odd = String.valueOf(x).length() % 2 != 0;

    int source = x;
    int result = 0;
    while (source > result) {
      result = result * 10 + source % 10;
      source = source / 10;
      //System.out.println(result+" | "+source);
      if (odd && result == source / 10) {
        return true;
      }
      if (!odd && result == source) {
        return true;
      }
    }
    return false;
  }


  public boolean _isPalindrome(int x) {
    if (x < 10) {
      return x > -1;
    }

    if (x % 10 == 0) {
      return false;
    }

    String str = String.valueOf(x);
    for (int i = 0, j = str.length() - 1; i <= j; i++, j--) {
      if (str.charAt(i) != str.charAt(j)) {
        return i == j;
      }
    }
    return true;
  }

  @Test
  public void test() {
    System.out.println(isPalindrome(12));
  }
}
