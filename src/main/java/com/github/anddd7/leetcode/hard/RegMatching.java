package com.github.anddd7.leetcode.hard;

import com.github.anddd7.leetcode.HighPerformance;

public class RegMatching {

  @HighPerformance("Dynamic Process")
  public boolean isMatch(String s, String p) {
    if (s == null || p == null) {
      return false;
    }

    // 考虑空字符串的情况, 因此增加一行一列
    // dp[i+1][j+1]: s(0,i)和p(0,j)能匹配成功
    boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

    // 1.如果s为空字符串的情况
    dp[0][0] = true; // p为空: 匹配成功
    for (int j = 0; j < p.length(); j++) {
      if (p.charAt(j) == '*') {
        // p[j]为*: s为空, 因此*只能匹配0个字符
        // 即s[0]和p(0,j-2)也是匹配成功的
        dp[0][j + 1] = dp[0][j - 1];
      }
    }

    // 2.如果p为空字符串
    // dp[i][0] = false

    // 3.s,p均不为空
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      for (int j = 0; j < p.length(); j++) {
        char reg = p.charAt(j);

        if (c == reg || reg == '.') {
          // 当前元素匹配成功, 结果取决于前面c(0,i-1)与p(0,j-1)匹配的结果
          dp[i + 1][j + 1] = dp[i][j];
        }

        if (reg == '*') {
          char previous = p.charAt(j - 1);
          if (c == previous || previous == '.') {
            // 当前元素和*的前置元素匹配成功, *可能匹配0,1或多个字符
            // 当前reg匹配0个, p(j-1,j)被当做空处理, 取决于前置p(0,j-2)的匹配结果
            boolean match0 = dp[i + 1][j - 1];
            // 当前reg匹配1个, p(j-1)匹配成功, 取决于前置p(0,j-1)匹配结果
            boolean match1 = dp[i + 1][j];
            // 当前reg匹配多个, s(0,i-1)和p(j)也匹配0或1或多次成功, 取决于上一字串s(0,i-1)与p(0,j)的匹配结果
            boolean matchMulti = dp[i][j + 1];

            dp[i + 1][j + 1] = match0 || match1 || matchMulti;
          } else {
            // 当前元素和*的前置元素不匹配, *只能匹配0个字符
            dp[i + 1][j + 1] = dp[i + 1][j - 1];
          }
        }
      }
    }

    return dp[s.length()][p.length()];
  }
}
