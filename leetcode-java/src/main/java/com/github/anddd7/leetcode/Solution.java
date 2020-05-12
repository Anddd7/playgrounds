package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public int regionsBySlashes(String[] grid) {
    int side = grid.length;
    DSU dsu = new DSU(side);
    for (int i = 0; i < side; i++) {
      for (int j = 0; j < side; j++) {
        int key = dsu.startIndex(i, j);
        char c = grid[i].charAt(j);

        if (c != '/') {
          dsu.union(key + 0, key + 1);
          dsu.union(key + 2, key + 3);
        }
        if (c != '\\') {
          dsu.union(key + 0, key + 3);
          dsu.union(key + 1, key + 2);
        }

        if (i < side - 1) {
          dsu.union(key + 2, dsu.startIndex(i + 1, j));
        }
        if (i > 0) {
          dsu.union(key, dsu.startIndex(i - 1, j) + 2);
        }

        if (j < side - 1) {
          dsu.union(key + 1, dsu.startIndex(i, j + 1) + 3);
        }
        if (j > 0) {
          dsu.union(key + 3, dsu.startIndex(i, j - 1) + 1);
        }

//        System.out.println(
//            "Checking at"
//                + " row:" + i
//                + " col:" + j
//                + " key:" + key
//                + " char: '" + c + "'"
//        );
//        dsu.printArea(i, j);
      }
    }

    return dsu.countLeader();
  }

  class DSU {

    private int side;
    private int[] items;

    public DSU(int n) {
      side = n;
      items = new int[4 * n * n];
      for (int i = 0; i < items.length; i++) {
        items[i] = i;
      }
    }

    // 向右上归并
    private void union(int x, int y) {
      if (items[x] < items[y]) {
        setLeader(y, findAndSetLeader(x));
      } else {
        setLeader(x, findAndSetLeader(y));
      }
    }

    private void setLeader(int index, int leader) {
      if (items[index] != index) {
        setLeader(items[index], leader);
      }
      items[index] = leader;
    }

    private int findAndSetLeader(int index) {
      if (items[index] != index) {
        items[index] = findAndSetLeader(items[index]);
      }
      return items[index];
    }


    // 获取某一个区域的start index
    private int startIndex(int row, int col) {
      return 4 * (row * side + col);
    }

    private void printArea(int row, int col) {
      int key = startIndex(row, col);
      System.out.println("\t" + items[key]);
      System.out.println(items[key + 3] + "\t" + "\t" + items[key + 1]);
      System.out.println("\t" + items[key + 2]);
    }

    public int countLeader() {
      int count = 0;
      for (int i = 0; i < items.length; i++) {
        if (items[i] == i) {
          count++;
        }
      }
      return count;
    }
  }
}
