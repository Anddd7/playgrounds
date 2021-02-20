package com.github.anddd7.leetcode;

public class Solution {

    public static final Solution INSTANCE = new Solution();

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int slot = 0;
        int i = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0) {
                int j = i;
                while (j < flowerbed.length && flowerbed[j] == 0) {
                    j++;
                }
                int length = j - i;
                if (i == 0) length++;
                if (j == flowerbed.length) length++;
                if (length >= 3) slot += (length - 1) / 2;
                i = j;
            } else i++;
        }

        return slot >= n;
    }
}
