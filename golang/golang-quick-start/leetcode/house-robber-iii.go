package leetcode

func rob(root *TreeNode) int {
	val := robMax(root)
	return maxOf(val[0], val[1])
}

func robMax(node *TreeNode) []int {
	if node == nil {
		return []int{0, 0}
	}
	leftMoney := robMax(node.Left)
	rightMoney := robMax(node.Right)
	selected := node.Val + leftMoney[1] + rightMoney[1]
	nonSelected := maxOf(leftMoney[0], leftMoney[1]) + maxOf(rightMoney[0], rightMoney[1])

	return []int{selected, nonSelected}
}

func maxOf(a int, b int) int {
	if a > b {
		return a
	}
	return b
}
