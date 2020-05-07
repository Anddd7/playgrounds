package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public boolean isSubtree(TreeNode s, TreeNode t) {
    if (t == null) {
      return true;
    }
    if (s == null) {
      return false;
    }
    return isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
  }

  public boolean isSameTree(TreeNode s, TreeNode t) {
    if (s == null && t == null) {
      return true;
    }
    if (s == null || t == null) {
      return false;
    }
    if (s.val != t.val) {
      return false;
    }
    return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
  }
}
