package leetcode

func intersect(nums1 []int, nums2 []int) []int {
	queryMap := make(map[int]int)
	result := []int{}

	for _, num := range nums1 {
		value, ok := queryMap[num]
		if ok {
			queryMap[num] = value + 1
		} else {
			queryMap[num] = 1
		}
	}

	for _, num := range nums2 {
		value, ok := queryMap[num]
		if ok && value > 0 {
			queryMap[num] = value - 1
			result = append(result, num)
		}
	}

	return result
}
