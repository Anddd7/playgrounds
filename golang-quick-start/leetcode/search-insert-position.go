package leetcode

func searchInsert(nums []int, target int) int {
	for i, value := range nums {
		if value >= target {
			return i
		}
	}
	return len(nums)
}
