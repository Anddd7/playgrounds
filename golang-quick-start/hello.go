package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println(permuteUnique([]int{1, 1, 2}))
}

func permuteUnique(nums []int) [][]int {
	sort.Ints(nums)
	length := len(nums)
	ans := [][]int{}
	temp := []int{}
	visited := make([]bool, length)

	var backtrack func(int)
	backtrack = func(idx int) {
		if idx == length {
			ans = append(ans, append([]int(nil), temp...))
			return
		}

		// 从 nums 中依次选取一个数字放到 idx 处
		for i, curr := range nums {
			// 剪枝: 当前数字已经被加入到了结果数组中
			if visited[i] {
				continue
			}
			// 剪枝: 当前数字和前置数字相同, 然而前置数字还未被使用过
			// e.g. 122 -> 1-22, 12-2, 得到答案回退, 1x2 (发现 2 前置还有一个 2 未使用, 此分支无效)
			if i > 0 && curr == nums[i-1] && !visited[i-1] {
				continue
			}

			// 当前合法串
			temp = append(temp, curr)
			// 标记当前数字已被使用
			visited[i] = true
			// 寻找下一个合法数字
			backtrack(idx + 1)
			// 结束搜索 回退状态
			visited[i] = false
			temp = temp[:len(temp)-1]
		}
	}
	// 启动
	backtrack(0)
	return ans
}

func swap(nums []int, x int, y int) {
	if x == y {
		return
	}
	temp := nums[y]
	nums[y] = nums[x]
	nums[x] = temp
}

// func maxOf(a int, b int) int {
// 	if a > b {
// 		return a
// 	}
// 	return b
// }
