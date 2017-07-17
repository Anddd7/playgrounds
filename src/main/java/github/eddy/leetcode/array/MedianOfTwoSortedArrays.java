package github.eddy.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class MedianOfTwoSortedArrays {

  /**
   * @date 2017/07/14
   * @description Challenge failed ,i was stumped by 'odd even' ,the complex situation is so
   * difficult i take divide thought , cut 1 array to 2 part (2 -> 4) ,but i lost in 'odd/even
   * judgement'
   *
   * there is a so 'powerful' solution and i study it so long for a while.And it base on 'find k'th
   * element in 2 array'.
   * @link http://blog.csdn.net/yutianzuijin/article/details/11499917
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int total = nums1.length + nums2.length;
    if (total % 2 == 0) {
      return (findKthInSortedArrays(nums1, nums1, total / 2)
          + findKthInSortedArrays(nums1, nums1, total / 2 + 1)) / 2;
    } else {
      return findKthInSortedArrays(nums1, nums1, total / 2 + 1);
    }
  }

  public double findKthInSortedArrays(int[] a, int[] b, Integer k) {
    int la = a.length, lb = b.length;

    //always assume that m is equal or smaller than n
    if (la > lb) {
      return findKthInSortedArrays(a, b, k);
    }

    if (la == 0) {
      return b[k - 1];
    }

    if (k == 1) {
      return Math.min(a[0], b[0]);
    }
    //divide k into two parts
    int pa = Math.min(k / 2, la), pb = k - pa;

    if (a[pa - 1] < b[pb - 1]) {

      return findKthInSortedArrays(
          Arrays.copyOfRange(a, la - pa, la),
          b,
          k - pa);
    } else if (a[pa - 1] > b[pb - 1]) {

      return findKthInSortedArrays(
          a,
          Arrays.copyOfRange(b, lb - pb, lb),
          k - pb);
    } else {

      return a[pa - 1];
    }
  }

  public String printArray(int[] arr) {
    List<String> list = new ArrayList<>();
    for (int a : arr) {
      list.add(String.valueOf(a));
    }
    return String.join(",", list);
  }

  @Test
  public void test() {
    MedianOfTwoSortedArrays test = new MedianOfTwoSortedArrays();
    System.out.println(
        test.findMedianSortedArrays(
            new int[]{1, 2, 3},
            new int[]{2, 4, 5}));
  }

  @Test
  public void test2() {
    System.out.println(
        printArray(
            Arrays.copyOfRange(new int[]{1, 2, 3, 4, 5},
                1,
                5)
        )
    );
  }
}

