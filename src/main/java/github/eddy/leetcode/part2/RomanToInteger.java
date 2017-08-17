package github.eddy.leetcode.part2;

import org.junit.Test;

public class RomanToInteger {

  public int romanToInt(String s) {
    int result = 0;
    for (int i = 0; i < s.length(); i++) {
      if (i == s.length() - 1) {
        result += getInt(s.charAt(i));
      } else {
        int current = getInt(s.charAt(i));
        int next = getInt(s.charAt(i + 1));
        result += current < next ? -current : current;
      }
    }
    return result;
  }

  public int getInt(char s) {
    switch (s) {
      case 'M':
        return 1000;
      case 'D':
        return 500;
      case 'C':
        return 100;
      case 'L':
        return 50;
      case 'X':
        return 10;
      case 'V':
        return 5;
      case 'I':
        return 1;
      default:
        return 0;
    }
  }

  @Test
  public void test() {
    System.out.println(romanToInt("MMMDLXXXVI"));
  }
}
