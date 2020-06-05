package com.github.anddd7.leetcode;

import java.util.Arrays;

class SolutionTest {

  public static void main(String[] args) {
    System.out.println(
        Arrays.toString(
            Solution.INSTANCE.spiralOrder(new int[][]{
                    new int[]{1, 2, 3},
                    new int[]{4, 5, 6},
                    new int[]{7, 8, 9}
                }
            )
        )
    );
  }
}
