package com.github.anddd7.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public int minDeletionSize(String[] A) {
    List<List<String>> groups = new ArrayList<>();
    groups.add(Arrays.asList(A));
    return deletableColumns(groups, 0, A[0].length());
  }

  private int deletableColumns(List<List<String>> groups, int index, int length) {
    if (groups.isEmpty()) {
      return 0;
    }
    if (index == length) {
      return 0;
    }

    List<List<String>> nextGroups = new ArrayList<>();

    for (int i = 0; i < groups.size(); i++) {
      List<String> strs = groups.get(i);

      for (int line = 1; line < strs.size(); line++) {
        char pre = strs.get(line - 1).charAt(index);
        char current = strs.get(line).charAt(index);
        if (current < pre) {
          return 1 + deletableColumns(groups, index + 1, length);
        }
        if (current == pre) {
          int start = line - 1;
          while (line + 1 < strs.size() && strs.get(line + 1).charAt(index) == current) {
            line++;
          }
          nextGroups.add(
              strs.subList(start, line + 1)
          );
        }
      }
    }

    return deletableColumns(nextGroups, index + 1, length);
  }
}
