package leetcode

func minimumTotal(triangle [][]int) int {
	dp := []int{0}

	for _, row := range triangle {
		for j, value := range row {
			if j == 0 {
				row[j] = dp[j] + value
			} else if j == len(dp) {
				row[j] = dp[j-1] + value
			} else {
				row[j] = minOf(dp[j-1]+value, dp[j]+value)
			}
		}
		dp = row
	}

	min := dp[0]
	for i := 1; i < len(triangle); i++ {
		min = minOf(min, dp[i])
	}

	return min
}

func minOf(a int, b int) int {
	if a < b {
		return a
	}
	return b
}
