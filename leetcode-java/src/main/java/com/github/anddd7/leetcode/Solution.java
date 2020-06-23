package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public String addBinary(String a, String b) {
    StringBuilder result = new StringBuilder();
    int next = 0;
    int pos = 0;
    while (pos < a.length() || pos < b.length()) {
      int posA = a.length() - 1 - pos;
      int posB = b.length() - 1 - pos;

      int x = posA < 0 ? 0 : a.charAt(posA) - '0';
      int y = posB < 0 ? 0 : b.charAt(posB) - '0';

      int rest = (x + y + next) >> 1;
      int value = (x + y + next) & 1;

      result.append(value);
      next = rest;
      pos++;
    }

    if (next != 0) {
      result.append(next);
    }

    return result.reverse().toString();
  }
}
