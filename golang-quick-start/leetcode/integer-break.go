package leetcode

func integerBreak(n int) int {
	dp := make([]int, 58)
	dp[2] = 1
	dp[3] = 2

	return integer(dp, n)
}

func integer(dp []int, n int) int {
	if dp[n] != 0 {
		return dp[n]
	}

	for i := 1; i <= n/2; i++ {
		right := integer(dp, n-i)

		number1 := i * (n - i)
		number2 := i * right
		number3 := dp[i] * right

		dp[n] = maxOf(dp[n], maxOf(number1, maxOf(number2, number3)))
	}
	return dp[n]
}

func maxOf(a int, b int) int {
	if a > b {
		return a
	}
	return b
}
