package leetcode

import "math"

func thirdMax(nums []int) int {
	if len(nums) == 1 {
		return nums[0]
	}

	heap := []int{
		nums[0],
		math.MinInt64,
		math.MinInt64,
	}

	for i := 1; i < len(nums); i++ {
		if !(nums[i] == heap[0] || nums[i] == heap[1] || nums[i] == heap[2]) {
			if nums[i] > heap[0] {
				heap[2] = heap[1]
				heap[1] = heap[0]
				heap[0] = nums[i]
			} else if nums[i] > heap[1] {
				heap[2] = heap[1]
				heap[1] = nums[i]
			} else if nums[i] > heap[2] {
				heap[2] = nums[i]
			}
		}
	}

	if heap[2] == math.MinInt64 {
		return heap[0]
	}
	return heap[2]
}
