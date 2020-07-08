package leetcode

func divingBoard(shorter int, longer int, k int) []int {
	if k == 0 {
		return nil
	}
	if shorter == longer {
		return []int{shorter * k}
	}

	lengths := make([]int, k+1)
	current := shorter * k
	dist := longer - shorter

	for i := 0; i <= k; i++ {
		lengths[i] = current
		current = current + dist
	}
	return lengths
}
