package com.github.anddd7.leetcode;

import com.github.anddd7.leetcode.Solution.TreeNode;

class SolutionTest {

  public static void main(String[] args) {
    TreeNode root = Solution.INSTANCE.recoverFromPreorder(
        "1-401--349---90--88"
    );
    System.out.println(
        root
    );
  }
}
