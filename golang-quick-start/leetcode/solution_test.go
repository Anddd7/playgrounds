package leetcode

import (
	"fmt"
	"testing"
)

func Test_uniquePathsWithObstacles(t *testing.T) {
	inputs := [][]int{
		{0, 0, 0},
		{0, 1, 0},
		{0, 0, 0},
	}
	// inputs := [][]int{
	// 	{1},
	// }

	// result := uniquePathsWithObstacles(inputs)
	result := uniquePathsWithObstacles_optimize(inputs)

	fmt.Println(result)
}
