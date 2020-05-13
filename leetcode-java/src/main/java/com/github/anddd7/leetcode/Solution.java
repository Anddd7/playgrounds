package com.github.anddd7.leetcode;

import java.util.ArrayList;
import java.util.Collections;
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

  public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
      return Collections.emptyList();
    }
    List<List<Integer>> result = new ArrayList<>();
    List<TreeNode> current = Collections.singletonList(root);
    while (!current.isEmpty()) {
      List<Integer> levelData = new ArrayList<>();
      current.forEach(n -> levelData.add(n.val));
      result.add(levelData);
      current = nextLevel(current);
    }
    return result;
  }

  private List<TreeNode> nextLevel(List<TreeNode> parents) {
    List<TreeNode> nodes = new ArrayList<>();
    parents.forEach(n -> {
      if (n.left != null) {
        nodes.add(n.left);

      }
      if (n.right != null) {
        nodes.add(n.right);
      }
    });

    return nodes;
  }
}
