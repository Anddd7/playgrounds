package com.github.anddd7.algorithms.easy

/**
 * 在给定数组中存在2个元素, 相加等于target
 */
class TwoSum {
    /**
     * - 排序, 从两端向中间靠拢查询: 取决于排序算法复杂度
     * - 循环一次, 通过HashMap快速确定值: O(n) | O(n)
     * - 双层循环: O(n2) | O(1)
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        if (nums.size == 2) return nums

        for (i in 0..(nums.size - 1)) {
            for (j in (i + 1)..(nums.size - 1)) {
                if (nums[i] + nums[j] == target) return intArrayOf(nums[i], nums[j])
            }
        }
        return IntArray(0)
    }
}