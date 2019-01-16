package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.LowPerformance;
import java.util.HashMap;
import java.util.Map;

/**
 * 最长非重复字符串
 */
public class LongestSubStrWithoutRepeat {

  @LowPerformance
  public int lengthOfLongestSubstring(String s) {
    if (s.length() == 1) {
      return 1;
    }

    int maxLength = 0;
    int length = 0;
    Map<Character, Integer> indexMap = new HashMap<>();

    char[] chars = s.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      Character c = chars[i];
      Integer previousIndex = indexMap.get(c);

      if (previousIndex != null) {
        if (maxLength < length) {
          maxLength = length;
        }
        length = 0;
        indexMap.clear();
      } else {
        length++;
        indexMap.put(c, i);
      }
    }
    return maxLength < length ? length : maxLength;
  }
}
