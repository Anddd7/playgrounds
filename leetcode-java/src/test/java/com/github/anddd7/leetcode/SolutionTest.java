package com.github.anddd7.leetcode;

import com.github.anddd7.leetcode.Solution.TreeNode;

class SolutionTest {

  public static void main(String[] args) {
    TreeNode root = Solution.INSTANCE.deserialize(
        "1,2,None,None,3,4,None,None,5,None,None"
    );
    System.out.println(
        Solution.INSTANCE.serialize(root)
    );
  }
}
