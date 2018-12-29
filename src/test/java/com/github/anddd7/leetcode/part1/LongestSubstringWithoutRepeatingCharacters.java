package com.github.anddd7.leetcode.part1;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class LongestSubstringWithoutRepeatingCharacters {

  /**
   * 2017/07/14
   *
   * Use a sliding window
   * if there appear repeating character ,slide current window to exclude previous repeating character
   */
  public int lengthOfLongestSubstring(String s) {
    Integer result = 0;

    //latest cut point
    Integer cutPoint = 0;
    //put character:latest index
    Map<Character, Integer> set = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      Character c = s.charAt(i);

      //character has appeared before
      if (set.containsKey(c)) {
        //flush cut point
        cutPoint = Math.max(set.get(c) + 1, cutPoint);
      }
      //update current char's index
      set.put(c, i);
      //compare with prev length
      result = Math.max(result, i - cutPoint + 1);
    }
    return result;
  }

  /**
   * same with me
   */
  public int _lengthOfLongestSubstring(String s) {
    int n = s.length(), ans = 0;
    Map<Character, Integer> map = new HashMap<>(); // current index of character
    // try to extend the range [i, j]
    for (int j = 0, i = 0; j < n; j++) {
      if (map.containsKey(s.charAt(j))) {
        i = Math.max(map.get(s.charAt(j)), i);
      }
      ans = Math.max(ans, j - i + 1);
      map.put(s.charAt(j), j + 1);
    }
    return ans;
  }

  @Test
  public void test() {
    LongestSubstringWithoutRepeatingCharacters test = new LongestSubstringWithoutRepeatingCharacters();
    System.out.println(
        test.lengthOfLongestSubstring(
            "ywpjscjfrhnyakdhiczbjfideaugelvekkpcmyjgfwghijqsoqtxxdacdwkjifpusgweefmmdtvlcmd")
    );
  }
}
