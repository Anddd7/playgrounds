package github.eddy.leetcode.part2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 * @author and777
 * @date 2018/1/2
 *
 * Given a digit string, return all possible letter combinations that the number could represent. A
 * mapping of digit to letters (just like on the telephone buttons) is given below. Input:Digit
 * string "23" Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 根据号码 ,给出可能的字符串
 */
public class LetterCombinOfPhoneNumber {

  /**
   * 取出之前的值再循环组合
   *
   * @param digits 号码串
   * @return 字母组合
   */
  public List<String> letterCombinations(String digits) {
    if (digits.isEmpty()) {
      return Collections.emptyList();
    }

    List<String> result = new ArrayList<>();
    char[] firstChars = getChars(digits.charAt(0));
    for (char first : firstChars) {
      result.add(String.valueOf(first));
    }

    for (int i = 1; i < digits.length(); i++) {
      List<String> temp = new ArrayList<>();
      for (String s : result) {
        for (char nextChar : getChars(digits.charAt(i))) {
          temp.add(s + nextChar);
        }
      }
      result = temp;
    }

    return result;
  }

  public char[] getChars(char number) {
    switch (number) {
      case '2':
        return new char[]{'a', 'b', 'c'};
      case '3':
        return new char[]{'d', 'e', 'f'};
      case '4':
        return new char[]{'g', 'h', 'i'};
      case '5':
        return new char[]{'j', 'k', 'l'};
      case '6':
        return new char[]{'m', 'n', 'o'};
      case '7':
        return new char[]{'p', 'q', 'r', 's'};
      case '8':
        return new char[]{'t', 'u', 'v'};
      case '9':
        return new char[]{'w', 'x', 'y', 'z'};
      default:
        return new char[0];
    }
  }

  @Test
  public void test() {
    System.out.println(letterCombinations("23").toString());
  }
}
