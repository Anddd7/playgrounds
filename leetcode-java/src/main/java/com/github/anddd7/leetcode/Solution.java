package com.github.anddd7.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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

  public String serialize(TreeNode root) {
    if (root == null) {
      return "";
    }
    List<String> result = new ArrayList<>();
    serialize(root, result);
    return String.join(",", result);
  }

  private void serialize(TreeNode node, List<String> storage) {
    if (node != null) {
      storage.add(String.valueOf(node.val));
      serialize(node.left, storage);
      serialize(node.right, storage);
    } else {
      storage.add("None");
    }
  }

  public TreeNode deserialize(String data) {
    if (data.isEmpty()) {
      return null;
    }
    String[] strs = data.split(",");
    TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);
    deserialize(root, strs, 1);
    return root;
  }

  private int deserialize(TreeNode current, String[] strs, int pos) {
    int leftIndex = pos;
    String left = strs[leftIndex];
    int endLeft;
    if ("None".equals(left)) {
      endLeft = leftIndex;
    } else {
      current.left = new TreeNode(Integer.parseInt(left));
      endLeft = deserialize(current.left, strs, leftIndex + 1);
    }

    int rightIndex = endLeft + 1;
    String right = strs[rightIndex];
    int endRight;
    if ("None".equals(right)) {
      endRight = rightIndex;
    } else {
      current.right = new TreeNode(Integer.parseInt(right));
      endRight = deserialize(current.right, strs, rightIndex + 1);
    }

    return endRight;
  }
}
