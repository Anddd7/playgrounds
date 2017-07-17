package github.eddy.leetcode.array;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class TwoSum {

  /**
   * 2017/07/13
   * O(n2)
   */
  public int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = nums.length - 1; j > i; j--) {
        if (nums[i] + nums[j] == target) {
          return new int[]{i, j};
        }
      }
    }
    return new int[]{-1, -1};
  }

  /**
   * O(n)
   * use map store prefix number
   */
  public int[] _twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int index = 0; index < nums.length; index++) {
      if (map.containsKey(nums[index])) {
        return new int[]{map.get(nums[index]), index};
      }
      map.put(target - nums[index], index);
    }
    return new int[]{-1, -1};
  }

  @Test
  public void test() {
    TwoSum twoSum = new TwoSum();
    twoSum.twoSum(new int[]{2, 7, 11, 15}, 9);
    twoSum._twoSum(new int[]{2, 7, 11, 15}, 9);
  }
}
