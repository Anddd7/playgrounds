package github.eddy.leetcode.part1;

import org.junit.Test;

public class PalindromeNumber {

  public boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    }
    if (x < 10) {
      return true;
    }

    int source = x;
    int result = 0;
    while (source > result) {
      result = result * 10 + source % 10;
      System.out.println(result+" | "+source);
      if (result == source) {
        return true;
      }
      source = source / 10;
      System.out.println(result+" | "+source);
      if (result == source) {
        return true;
      }
    }
    return false;
  }

  @Test
  public void test() {
    System.out.println(isPalindrome(10));
  }
}
