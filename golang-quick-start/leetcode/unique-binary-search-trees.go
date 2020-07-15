package leetcode

func numTrees(n int) int {
	G := make([]int, n+1)
	G[0], G[1] = 1, 1

	for length := 2; length <= n; length++ {
		for pos := 0; pos < length; pos++ {
			G[length] += G[pos] * G[length-pos-1]
		}
	}
	return G[n]
}
