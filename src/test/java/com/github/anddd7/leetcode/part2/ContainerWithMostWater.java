package com.github.anddd7.leetcode.part2;

public class ContainerWithMostWater {

  public int maxArea(int[] height) {
    int l = 0, r = height.length - 1;
    int max = 0;

    while (l < r) {
      max = Math.max(max, (r - l) * Math.min(height[l], height[r]));
      /**
       * at first ,width = height.length-1 ,is biggest
       * //
       * so ,if you want to find bigger area ,the height must higher than min(height[0], height[height.length - 1])
       * so ,if height[l] < height[r] ,we need find next left line ,Math.min(height[l], height[r]) maybe change
       * same reason ,if right line is shorter ,find next longer right line.
       *
       * 宽度不断缩小 ,想要面积变大必须获得更大的高度 ,所以不断淘汰较短的线
       * 最后留下的线中必然有一个是最长的 ,然后另一条线不断靠近 ,并检查是否刷新最大值
       */
      if (height[l] < height[r]) {
        l++;
      } else {
        r--;
      }
    }
    return max;
  }

  /**
   * TimeLimit
   */
  public int _maxArea(int[] height) {
    if (height.length == 2) {
      return Math.min(height[0], height[1]);
    }

    int max = 0;
    for (int i = 0; i < height.length - 1; i++) {
      for (int j = i + 1; j < height.length; j++) {
        max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
      }
    }
    return max;
  }
}
