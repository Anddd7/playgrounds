package leetcode

func minPathSum(grid [][]int) int {
	length := len(grid[0])
	dp := make([]int, length)

	for i, row := range grid {
		current := dp
		for j, value := range row {
			if j == 0 {
				current[0] = dp[0] + value
			} else if i == 0 {
				current[j] = current[j-1] + value
			} else {
				current[j] = minOf(current[j-1], dp[j]) + value
			}
		}
		dp = current
	}

	return dp[length-1]
}
