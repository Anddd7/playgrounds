package github.eddy.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class ThreeSum {

  /**
   * 2017/07/13
   *
   * O(n2)
   * My method is basic on {@link TwoSum#twoSum(int[], int)} to find 'a+b = -c'
   * And ignore adjacent repeat number.
   */
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList();

    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        //System.out.println("跳过首位:" + nums[i]);
        continue;
      }
      int sum = 0 - nums[i];

      Set<Integer> set = new HashSet();
      Integer pre = null;
      for (int j = i + 1; j < nums.length; j++) {
        if (pre != null && pre.equals(nums[j])) {
          //System.out.println(nums[i]+"跳过待选:" + nums[j]);
        } else if (set.contains(sum - nums[j])) {
          //System.out.println("成功匹配:" + Arrays.asList(nums[i], sum - nums[j], nums[j]));
          result.add(Arrays.asList(nums[i], sum - nums[j], nums[j]));
          pre = nums[j];
        } else {
          //System.out.println(nums[i]+"进入待选:" + nums[j]);
          set.add(nums[j]);
        }
      }
    }
    return result;
  }








  @Test
  public void test() {
    threeSum(
        new int[]{-12, 12, -5, -4, -12, 11, 9, -11, 13, 1, 12, -1, 8, 1, -9, -11, -13, -4, 10, -9,
            -6, -11, 1, -15, -3, 4, 0, -15, 3, 6, -4, 7, 3, -2, 10, -2, -6, 4, 2, -7, 12, -1, 7, 6,
            7, 6, 2, 10, -13, -3, 8, -12, 2, -5, -12, 6, 6, -5, 6, -5, -14, 9, 9, -4, -8, 4, 2, -7,
            -15, -11, -7, 12, -4, 8, -5, -12, -1, 12, 5, 1, -5, -1, 5, 12, 9, 0, 3, 0, 3, -14, 2,
            -4, 2, -4, 0, 1, 7, -13, 9, -1, 13, -12, -11, -6, 11, -1, -10, -5, -3, 10, 3, 7, -6,
            -15, -4, 10, 1, 14, -12}).forEach(System.out::println);
  }
}
