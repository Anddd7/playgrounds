package github.eddy.algorithm;

import java.util.Collections;
import java.util.Comparator;

/**
 * 二分搜索: 基于有序序列, 确定元素在左边还是右边, 依次缩小范围
 *
 * @see Collections#binarySearch
 */
public class BinarySearch {

  static <T extends Comparable<T>> T search_loop(T[] array, T target) {
    return search(array, target, Comparable::compareTo);
  }

  static <T extends Comparable<T>> T search_recursive(T[] array, T target) {
    return search(array, 0, array.length - 1, target, Comparable::compareTo, 1);
  }

  static <T> T search(T[] array, T target, Comparator<T> comparator) {
    int count = 0;
    T result = null;

    // start
    int left = 0;
    int right = array.length - 1;

    while (left <= right) {
      count++;

      int mid = (right + left) >>> 1;
      int c = comparator.compare(target, array[mid]);
      if (c == 0) {
        result = array[mid];
        break;
      }
      if (c < 0) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    // end

    System.out.println(String.format("搜索结束, 循环次数: %d, 搜索结果: %s", count, result));
    return result;
  }

  static <T> T search(
      T[] array, int left, int right,
      T target, Comparator<T> comparator,
      int deep) {
    if (left > right) {
      System.out.println(String.format("搜索结束, 递归次数: %d, 搜索失败: null", deep));
      return null;
    }

    int mid = (right + left) >>> 1;
    int c = comparator.compare(target, array[mid]);
    if (c == 0) {
      System.out.println(String.format("搜索结束, 递归次数: %d, 搜索成功: %s", deep, array[mid]));
      return array[mid];
    }
    if (c < 0) {
      return search(array, left, mid - 1, target, comparator, deep + 1);
    } else {
      return search(array, mid + 1, right, target, comparator, deep + 1);
    }
  }
}
