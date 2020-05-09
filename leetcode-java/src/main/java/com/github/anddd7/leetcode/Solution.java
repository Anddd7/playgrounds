package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  class RLEIterator {

    private int[] flags;
    private int indexOfTime = 0;
    private int indexOfNumber = 1;

    public RLEIterator(int[] A) {
      flags = A;
    }

    private boolean isEmpty() {
      return indexOfTime == flags.length;
    }

    public int next(int n) {
      while (n > 0 && !isEmpty()) {
        int time = flags[indexOfTime];
        if (n > time) {
          n -= time;
          indexOfTime += 2;
          indexOfNumber += 2;
        } else {
          flags[indexOfTime] = time - n;
          return flags[indexOfNumber];
        }
      }
      return -1;
    }
  }
}
