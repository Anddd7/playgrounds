package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.HighPerformance;
import java.util.HashMap;
import java.util.Map;

/**
 * 最长非重复字符串
 */
public class LongestSubStrWithoutRepeat {

  /**
   * 按顺序遍历, 将元素和index依次放入map中
   * 发现已有相同元素时, 记录中间字串的长度: abc[a] -> abc -> 3
   * 从原有的非重复字符开始计算新的字串: [a]bca -> bca -> 3
   * - 只需要剔除掉重复的头即可
   * 避免相同字符中间可能已经存在其他重复的字符: [a]bb[a]
   * - 记录最近一个比对字串的起点, 如果有相同字符的index超出这个范围, 也被认为是不重复的(在当前substring中不重复)
   *
   * 因此只需要遍历一次字符串即可
   */
  @HighPerformance("O[n]")
  public int lengthOfLongestSubstring(String s) {
    if (s.length() == 1) {
      return 1;
    }

    Map<Character, Integer> indexMap = new HashMap<>();
    char[] chars = s.toCharArray();

    int maxLength = 0;
    int length = 0;
    int lastRepeatIndex = -1;

    for (int i = 0; i < chars.length; i++) {
      Character c = chars[i];
      Integer previousIndex = indexMap.put(c, i);

      if (previousIndex == null || previousIndex < lastRepeatIndex) {
        length++;
        continue;
      }

      if (maxLength < length) {
        maxLength = length;
      }
      length = i - previousIndex;
      lastRepeatIndex = previousIndex;
    }
    // compare last substring with max-length
    return maxLength < length ? length : maxLength;
  }
}
