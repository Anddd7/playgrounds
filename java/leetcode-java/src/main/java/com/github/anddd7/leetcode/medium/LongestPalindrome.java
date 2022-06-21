package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.HighPerformance;
import com.github.anddd7.leetcode.NormalPerformance;

/**
 * 最长回文串
 */
public class LongestPalindrome {


  public String longestPalindrome(String s) {
//    return checkExtendString(s);
    return checkLongerString(s);
  }

  /**
   * 从(s,n)向中间靠拢, 寻找最大回文串
   * 下一个期望的回文串应该比当前的大, 所以可以预先算出(s,n)
   *
   * - 如果判断是 aba 回文串就不会再判断 abba
   */
  @HighPerformance("O[n^2/2]")
  private String checkLongerString(String s) {
    if (s.length() < 2) {
      return s;
    }

    char[] ca = s.toCharArray();
    int rs = 0, re = 0;
    int max = 0;

    for (int i = 0; i < ca.length; i++) {
      if (isPalindrome(ca, i - max - 1, i)) {
        rs = i - max - 1;
        re = i;
        max += 2;
      } else if (isPalindrome(ca, i - max, i)) {
        rs = i - max;
        re = i;
        max += 1;
      }
    }
    return s.substring(rs, re + 1);
  }

  private boolean isPalindrome(char[] ca, int s, int e) {
    if (s < 0) {
      return false;
    }

    while (s < e) {
      if (ca[s++] != ca[e--]) {
        return false;
      }
    }
    return true;
  }


  /**
   * 从k(k+1)向两边延伸, 寻找最大的回文串
   */
  @NormalPerformance("O[n^2]")
  private String checkExtendString(String s) {
    if (s.length() < 2) {
      return s;
    }

    String result = "";
    for (int i = 0; i < s.length() - 1; i++) {
      // 从i向两边找
      String s1 = extend(s, i, i);
      if (s1.length() > result.length()) {
        result = s1;
      }

      // 从i,i+1向两边找
      String s2 = extend(s, i, i + 1);
      if (s2.length() > result.length()) {
        result = s2;
      }
    }
    return result;
  }

  private String extend(String s, int i, int j) {
    while (0 <= i && j < s.length() && s.charAt(i) == s.charAt(j)) {
      i--;
      j++;
    }
    return i + 1 < j ? s.substring(i + 1, j) : "";
  }
}
