package github.eddy.leetcode.array;

import java.util.Arrays;
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
      return (findKthInSortedArrays(nums1, nums2, total / 2)
          + findKthInSortedArrays(nums1, nums2, total / 2 + 1)) / 2;
    } else {
      return findKthInSortedArrays(nums1, nums2, total / 2 + 1);
    }
  }

  public double findKthInSortedArrays(int[] left, int[] right, Integer k) {
    int sizeL = left.length, sizeR = right.length;

    //keep left is shorter
    if (sizeL > sizeR) {
      return findKthInSortedArrays(right, left, k);
    }

    //if left is empty , the k'th element must be in right
    if (sizeL == 0) {
      return right[k - 1];
    }

    //find first element
    if (k == 1) {
      return Math.min(left[0], right[0]);
    }

    //divide k into two parts ,equals find k/2 in both array
    //if k/2 is more than sizeL ,the k'th element must in right ,so exclude all in left and k-sizeL elements in right
    //next call with return right[k - 1];
    int pa = Math.min(k / 2, sizeL), pb = k - pa;

    /**
     *            A                B
     * left   ..........[pa-1]........
     * right ..........[pb-1]........
     */
    if (left[pa - 1] < right[pb - 1]) {
      /**
       * @thinking
       * the index of right[pb - 1] is bigger than left[pa - 1] in whole array ,
       *              A               C               B
       * whole ...........[pa-1].......[pb-1].................
       *
       * left:A and right:A form whole:A+C(part1 ,the part in right:A which bigger than left[pa-1] less than right[pb-1])
       * left:B and right:B form whole:B+C(part2 ,the part in left:B which bigger than left[pa-1] less than right[pb-1])
       *
       * the whole:A (pa+pb-C.part1.length=k-C.part1.length) is less than k ,
       * the whole:A+C(pa+pb+C.part2.length=k+C.part2.length) is more than k
       *
       * @result so the k'th is must in (left:B, right:A)
       * @next exclude elements less than left[pa-1] (k'th can't exist in it) ,find (k-pa)'th element
       */
      return findKthInSortedArrays(
          Arrays.copyOfRange(left, pa, sizeL),
          right,
          k - pa);
    } else if (left[pa - 1] > right[pb - 1]) {
      //as above ,but exclude right:A
      return findKthInSortedArrays(
          left,
          Arrays.copyOfRange(right, pb, sizeR),
          k - pb);
    } else {
      /**
       *                A                             B
       * whole ..............[pa-1][pb-1].....................
       * whole:A has pa+pb=k elements ,[pb-1] is k'th
       */
      return left[pa - 1];
    }
  }


  @Test
  public void test() {
    MedianOfTwoSortedArrays test = new MedianOfTwoSortedArrays();
    System.out.println(
        test.findMedianSortedArrays(
            new int[]{1, 2},
            new int[]{3, 4}));
  }

}

