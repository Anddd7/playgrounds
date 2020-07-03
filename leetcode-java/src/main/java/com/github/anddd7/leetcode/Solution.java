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

  public TreeNode sortedArrayToBST(int[] nums) {
    return getTree(nums, 0, nums.length - 1);
  }

  public TreeNode getTree(int[] nums, int start, int end) {
    if (start == end) {
      return new TreeNode(nums[start]);
    }
    if (start > end) {
      return null;
    }

    // 顶点向右靠
//    int parentIndex = (start + end + 1) >> 1;
    // 顶点向左靠
    int parentIndex = (start + end) >> 1;
    TreeNode parent = new TreeNode(nums[parentIndex]);
    parent.left = getTree(nums, start, parentIndex - 1);
    parent.right = getTree(nums, parentIndex + 1, end);
    return parent;
  }
}
