package com.github.anddd7.leetcode;

class SolutionTest {

  public static void main(String[] args) {
    System.out.println(
        Solution.INSTANCE.kthSmallest(
            new int[][]{
                new int[]{1, 5, 9},
                new int[]{10, 11, 13},
                new int[]{12, 13, 15}
            },
            8
        )
    );
  }
}
