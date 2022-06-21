package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.HighPerformance;
import com.github.anddd7.leetcode.NormalPerformance;

/**
 * I   1
 * V   5
 * X   10
 * L   50
 * C   100
 * D   500
 * M   1000
 */

public class Integer2Roman {

  public String intToRoman(int num) {
//    return rangeMapping(num);
    return fullMapping(num);
  }

  @HighPerformance("90%")
  public String fullMapping(int num) {
    String M[] = {"", "M", "MM", "MMM"};
    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
  }

  @NormalPerformance("50%")
  public String rangeMapping(int num) {
    StringBuilder result = new StringBuilder();

    int step = 0;
    while (num > 0) {
      if (num < values[step]) {
        step++;
      } else {
        num -= values[step];
        result.append(symbols[step]);
      }
    }
    return result.toString();
  }

  private int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
  private String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
}
