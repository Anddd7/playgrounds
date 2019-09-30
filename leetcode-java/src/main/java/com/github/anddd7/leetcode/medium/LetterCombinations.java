package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.HighPerformance;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LetterCombinations {

  private char[][] table = new char[][]{
      new char[]{'a', 'b', 'c'},
      new char[]{'d', 'e', 'f'},
      new char[]{'g', 'h', 'i'},
      new char[]{'j', 'k', 'l'},
      new char[]{'m', 'n', 'o'},
      new char[]{'p', 'q', 'r', 's'},
      new char[]{'t', 'u', 'v'},
      new char[]{'w', 'x', 'y', 'z'}
  };

  /**
   * 内存改进 (实际生产中意义不大, list的影响较小)
   * - 使用数组替换list
   * - 使用原生的数组copy后插入新增的元素, 减少list的重复创建和替换
   */
  @HighPerformance("100%, but memory is only 13.98%")
  public List<String> letterCombinations(String digits) {
    if (digits.isEmpty()) {
      return Collections.emptyList();
    }
    return subString(digits.toCharArray(), 0);
  }

  private List<String> subString(char[] chars, int index) {
    if (index > chars.length - 1) {
      return Collections.singletonList("");
    }

    char number = chars[index];
    char[] current = table[number - '2'];
    List<String> subs = subString(chars, index + 1);
    List<String> result = new ArrayList<>(current.length * subs.size());
    for (char c : current) {
      for (String s : subs) {
        result.add(c + s);
      }
    }
    return result;
  }
}
