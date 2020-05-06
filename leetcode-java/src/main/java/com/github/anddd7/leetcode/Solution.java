package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  /**
   * 动态规划, temp[i] = min(temp[i-1]+cost1[0], temp[i-7]+cost1[1], temp[i-30]+cost[2])
   * <p>
   * 超界的和没有出行的日期都 = 0
   */
  public int mincostTickets(int[] days, int[] costs) {
    int minDay = days[0];
    int maxDay = days[days.length - 1];
    int[] temp = new int[maxDay + 1];

    for (int d = minDay, i = 0; d < maxDay + 1; d++) {
      if (d == days[i]) {
        int previous0 = getPreviousCost(temp, d, 1);
        int c0 = costs[0] + previous0;

        int previous7 = getPreviousCost(temp, d, 7);
        int c7 = costs[1] + previous7;

        int previous30 = getPreviousCost(temp, d, 30);
        int c30 = costs[2] + previous30;

        temp[d] = Math.min(c0, Math.min(c7, c30));

        i++;
      } else {
        temp[d] = temp[d - 1];
      }
    }

    return temp[maxDay];
  }

  private int getPreviousCost(int[] temp, int day, int num) {
    return day < num ? 0 : temp[day - num];
  }
}
