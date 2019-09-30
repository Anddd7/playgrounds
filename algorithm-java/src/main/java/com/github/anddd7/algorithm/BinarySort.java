package com.github.anddd7.algorithm;

import java.util.Comparator;
import java.util.List;

/**
 * 二分插入排序法: 将数组元素依次插入一个已排序的新数组中, 按照二分查找法查找待插入位置
 *
 * @see BinarySearch
 * @see java.util.TimSort#binarySort
 *
 * TimSort 采用 分区(归并)+二分插入排序的方式 进行排序
 * @see java.util.Collections#sort(List)
 * @see java.util.TimSort#sort
 */
class BinarySort {

  static <T extends Comparable<T>> T[] sort_loop(T[] list) {
    return sort(list, Comparable::compareTo);
  }

  private static <T> T[] sort(T[] list, Comparator<T> comparator) {
    for (int i = 1; i < list.length; i++) {
      insert(list, i, comparator);
    }
    return list;
  }

  private static <T> void insert(T[] array, int start, Comparator<T> comparator) {
    int left = 0;
    int right = start;
    T target = array[start];

    while (left < right) {
      int mid = (right + left) >>> 1;
      int c = comparator.compare(target, array[mid]);
      if (c < 0) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    int n = start - left;
    // ignore copy length=0

    if (n == 0) {
      return;
    }
    if (n == 2) {
      array[left + 2] = array[left + 1];
      array[left + 1] = array[left];
    } else if (n == 1) {
      array[left + 1] = array[left];
    } else {
      System.out
          .println(String.format("Copy Array: %d -> %d [%d]", left, left + 1, start - left));
      System.arraycopy(
          array, left,
          array, left + 1,
          start - left
      );
    }
    array[left] = target;
  }
}