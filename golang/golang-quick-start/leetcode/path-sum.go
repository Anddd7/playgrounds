package leetcode

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func hasPathSum(root *TreeNode, sum int) bool {
	if root == nil {
		return false
	}

	return hasChildPathSum(root, sum)
}

func hasChildPathSum(node *TreeNode, sum int) bool {
	value := node.Val
	if node.Left != nil && node.Right != nil {
		return hasChildPathSum(node.Left, sum-value) || hasChildPathSum(node.Right, sum-value)
	}
	if node.Left != nil {
		return hasChildPathSum(node.Left, sum-value)
	}
	if node.Right != nil {
		return hasChildPathSum(node.Right, sum-value)
	}
	return sum == value
}
