package leetcode

// type TreeNode struct {
// 	Val   int
// 	Left  *TreeNode
// 	Right *TreeNode
// }

func generateTrees(n int) []*TreeNode {
	if n == 0 {
		return nil
	}

	return generateTreesFrom(1, n)
}

func generateTreesFrom(start int, end int) []*TreeNode {
	if start > end {
		return []*TreeNode{nil}
	}
	if start == end {
		return []*TreeNode{{Val: start, Left: nil, Right: nil}}
	}

	trees := []*TreeNode{}
	for top := start; top <= end; top++ {
		leftTrees := generateTreesFrom(start, top-1)
		rightTrees := generateTreesFrom(top+1, end)

		for _, left := range leftTrees {
			for _, right := range rightTrees {
				trees = append(trees, &TreeNode{Val: top, Left: left, Right: right})
			}
		}
	}

	return trees
}
