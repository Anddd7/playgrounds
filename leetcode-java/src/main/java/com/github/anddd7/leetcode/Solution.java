package com.github.anddd7.leetcode;

import java.util.Arrays;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  int[] state;

  public boolean equationsPossible(String[] equations) {
    state = new int[26];
    for (int i = 0; i < state.length; i++) {
      state[i] = i;
    }

    for (String equation : equations) {
      int x = equation.charAt(0) - 'a';
      int y = equation.charAt(3) - 'a';
      boolean isEquals = equation.charAt(1) == '=';

      if (isEquals) {
        union(x, y);
      }
    }

    System.out.println(Arrays.toString(state));

    for (String equation : equations) {
      int x = equation.charAt(0) - 'a';
      int y = equation.charAt(3) - 'a';
      boolean isEquals = equation.charAt(1) == '=';

      if (!isEquals && find(x) == find(y)) {
        return false;
      }
    }
    return true;
  }

  private void union(int x, int y) {
    state[find(x)] = find(y);
  }

  private int find(int i) {
    if (state[i] == i) {
      return i;
    }
    int parent = find(state[i]);
    state[i] = parent;
    return parent;
  }
}
