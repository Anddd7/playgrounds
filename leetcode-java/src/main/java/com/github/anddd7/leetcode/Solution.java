package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public int maximalSquare(char[][] matrix) {
    int side = '0';
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == '1') {
          int previous = i == 0 || j == 0 ?
              '0'
              : Math.min(
                  matrix[i - 1][j - 1],
                  Math.min(matrix[i - 1][j], matrix[i][j - 1])
              );
          matrix[i][j] = (char) (1 + previous);
          side = Math.max(side, matrix[i][j]);
        }
      }
    }
    return (side - '0') * (side - '0');
  }
}
