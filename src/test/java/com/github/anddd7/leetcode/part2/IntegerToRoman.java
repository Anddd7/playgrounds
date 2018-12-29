package com.github.anddd7.leetcode.part2;

import java.util.Arrays;
import org.junit.Test;

public class IntegerToRoman {

  public String _intToRoman(int num) {
    String M[] = {"", "M", "MM", "MMM"};
    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    return M[num/1000] + C[(num%1000)/100]+ X[(num%100)/10] + I[num%10];
  }

  public String intToRoman(int num) {
    String result = "";
    if (num == 0) {
      result = "";
    } else if (num >= 1000) {
      result = append(num / 1000, 'M') + intToRoman(num % 1000);
    } else if (num >= 900) {
      result = "CM" + intToRoman(num - 900);
    } else if (num >= 500) {
      result = append(num / 500, 'D') + intToRoman(num % 500);
    } else if (num >= 400) {
      result = "CD" + intToRoman(num - 400);
    } else if (num >= 100) {
      result = append(num / 100, 'C') + intToRoman(num % 100);
    } else if (num >= 90) {
      result = "XC" + intToRoman(num - 90);
    } else if (num >= 50) {
      result = append(num / 50, 'L') + intToRoman(num % 50);
    } else if (num >= 40) {
      result = "XL" + intToRoman(num - 40);
    } else if (num >= 10) {
      result = append(num / 10, 'X') + intToRoman(num % 10);
    } else if (num >= 9) {
      result = "IX" + intToRoman(num - 9);
    } else if (num >= 5) {
      result = append(num / 5, 'V') + intToRoman(num % 5);
    } else if (num >= 4) {
      result = "IV" + intToRoman(num - 4);
    } else if (num >= 1) {
      result = append(num, 'I');
    }
    return result;
  }

  public String append(int num, char c) {
    char[] temp = new char[num];
    Arrays.fill(temp, c);
    return String.valueOf(temp);
  }

  @Test
  public void test() {
    System.out.println(intToRoman(1986));
  }
}
