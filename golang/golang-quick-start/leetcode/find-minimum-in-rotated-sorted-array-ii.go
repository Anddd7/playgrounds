package leetcode

func minArray_(numbers []int) int {
	for i := 1; i < len(numbers); i++ {
		if numbers[i] < numbers[i-1] {
			return numbers[i]
		}
	}
	return numbers[0]
}

func minArray(numbers []int) int {
	return minArrayFrom(numbers, 0, len(numbers)-1)
}

func minArrayFrom(numbers []int, start int, end int) int {
	if start == end {
		return numbers[start]
	}

	mid := (start + end) >> 1
	target := numbers[mid]

	if target < numbers[end] {
		return minArrayFrom(numbers, start, mid)
	}
	if target > numbers[end] {
		return minArrayFrom(numbers, mid+1, end)
	}
	return minArrayFrom(numbers, start, end-1)
}
