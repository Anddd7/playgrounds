package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.HighPerformance;
import com.github.anddd7.leetcode.NormalPerformance;

/**
 * 字符串化为曲线排列再输出
 *
 * PAYPALISHIRING
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 */
public class ZigZag {

  /**
   * 首尾两行只包含顶点的元素
   * 中间的行包含斜边的元素
   *
   * 将一个zig作为一组, length = 3 + (3-2) = 4
   *
   * 第一行的坐标为 0 0+4 0+8 ...
   * 第二行的坐标为 1 1+4,0+4-1 1+8,0+8-1 ... (下一个loop向前-1)
   * 第三行的坐标为 2 2+4 2+8 ...
   */
  @NormalPerformance
  public String convert(String s, int numRows) {
    if (s.length() < 2 || numRows == 1) {
      return s;
    }

    StringBuilder result = new StringBuilder();

    for (int row = 0; row < numRows; row++) {
      StringBuilder rowString = new StringBuilder();

      int index = row;
      while (index < s.length()) {
        int nextBaseIndex = index + numRows + (numRows - 2);

        if (row == 0 || row == numRows - 1) {
          rowString.append(s.charAt(index));
        } else {
          rowString.append(s.charAt(index));

          int inner = nextBaseIndex - row - row;
          if (inner < s.length()) {
            rowString.append(s.charAt(inner));
          }
        }
        index = nextBaseIndex;
      }
      result.append(rowString);
    }
    return result.toString();
  }
}
