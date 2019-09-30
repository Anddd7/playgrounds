package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.HighPerformance;
import java.util.LinkedList;
import java.util.List;

public class GenerateParentheses {

  private List<String> results = new LinkedList<>();

  /**
   * backtrack, build it as a tree
   * - first should be "(", last should be ")"
   * - number of each bracket is n
   *
   * backtrack:
   * - put `(`
   *
   */
  @HighPerformance("100%")
  public List<String> generateParenthesis(int n) {
    backtrack("", n, n);
    return results;
  }

  private void backtrack(String str, int leftBracketCount, int rightBracketCount) {
    if (rightBracketCount == 0) {
      results.add(str);
    }

    if (leftBracketCount > 0) {
      backtrack(str + "(", leftBracketCount - 1, rightBracketCount);
    }
    // last char should be right bracket, so it must more than left
    if (rightBracketCount > 0 & rightBracketCount > leftBracketCount) {
      backtrack(str + ")", leftBracketCount, rightBracketCount - 1);
    }
  }
}
