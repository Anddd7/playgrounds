package leetcode

func uniquePathsWithObstacles(obstacleGrid [][]int) int {
	maxY := len(obstacleGrid)
	maxX := len(obstacleGrid[0])
	/*
		// 动态数组(size在runtime才确定的)创建需要用make
		dp := make([][]int, maxY+1)
		// 多维数组
		for i := range dp {
			dp[i] = make([]int, maxX+1)
		}
	*/

	// 这种方法能够创建连续内存的数组, 效率更高但不好理解
	// https://stackoverflow.com/questions/39804861/what-is-a-concise-way-to-create-a-2d-slice-in-go
	// https://play.golang.org/p/8gulJLAWaEe
	dp := make([][]int, maxY+1)
	rows := make([]int, (maxY+1)*(maxX+1))
	for i := 0; i < maxY+1; i++ {
		dp[i] = rows[i*(maxX+1) : (i+1)*(maxX+1)]
	}

	dp[1][1] = 1 - obstacleGrid[0][0]

	for y := 0; y < maxY; y++ {
		for x := 0; x < maxX; x++ {
			if obstacleGrid[y][x] == 0 {
				dp[y+1][x+1] += dp[y][x+1] + dp[y+1][x]
			}
		}
	}

	return dp[maxY][maxX]
}

func uniquePathsWithObstacles_optimize(obstacleGrid [][]int) int {
	maxY := len(obstacleGrid)
	maxX := len(obstacleGrid[0])
	/*
		滚动数组(滚动数据)降维
		写出状态方程 f[i][j] = f[i-1][j] + f[i][j-1] 是递进向前的, 不会依赖f[i-n][j-m]的数据
		拆解成2段, 那么我们只需要保留一个一维数组即可
		f[i][j] += f[i-1][j]
		f[i][j] += f[i][j-1]
	*/
	dp := make([]int, maxX)
	dp[0] = 1 - obstacleGrid[0][0]

	for y := 0; y < maxY; y++ {
		for x := 0; x < maxX; x++ {
			if obstacleGrid[y][x] == 1 {
				dp[x] = 0
				continue
			}
			if x > 0 && obstacleGrid[y][x-1] == 0 {
				dp[x] += dp[x-1]
			}
		}
	}

	return dp[maxX-1]
}
