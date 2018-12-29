package com.github.anddd7.leetcode.part1;

import org.junit.Test;

public class StringToInteger {

  /**
   * check if there have 2 operators / start with word  , return last correct integer
   */
  public int myAtoi(String str) {
    String source = str.trim();

    long result = 0;
    int index = 0;

    int flag = 1;
    if (source.startsWith("-") || source.startsWith("+")) {
      flag = source.startsWith("-") ? -1 : 1;
      index++;
    }

    char[] characters = source.toCharArray();
    for (; index < characters.length; index++) {
      Character character = characters[index];
      if (character.compareTo('0') < 0 || character.compareTo('9') > 0) {
        break;
      }
      result = result * 10 + Integer.parseInt(character.toString());
      if (Integer.MAX_VALUE < flag * result) {
        return Integer.MAX_VALUE;
      }
      if (Integer.MIN_VALUE > flag * result) {
        return Integer.MIN_VALUE;
      }
    }
    return (int) (flag * result);
  }

  @Test
  public void test() {
    System.out.println(myAtoi("-+1"));
  }
}
