package com.github.anddd7.leetcode.medium;

import com.github.anddd7.leetcode.HighPerformance;
import com.github.anddd7.leetcode.LowPerformance;

public class ContainerWithMostWater {

  public int maxArea(int[] height) {
//    return scanAll(height);
    return diminishing(height);
  }

  @HighPerformance("99%")
  private int diminishing(int[] height) {
    int max = 0;
    int start = 0, end = height.length - 1;

    while (start < end) {
      int width = end - start;

      if (height[start] > height[end]) {
        max = Math.max(max, width * height[end]);
        end--;
      } else {
        max = Math.max(max, width * height[start]);
        start++;
      }
    }

    return max;
  }

  @LowPerformance("25%, O[n^2]")
  private int scanAll(int[] height) {
    int max = 0;

    for (int i = 0; i < height.length; i++) {
      for (int j = i + 1; j < height.length; j++) {
        int volume = (j - i) * Math.min(height[i], height[j]);
        if (volume > max) {
          max = volume;
        }
      }
    }
    return max;
  }
}
