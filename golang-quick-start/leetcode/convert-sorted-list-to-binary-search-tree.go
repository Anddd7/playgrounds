package leetcode

type ListNode struct {
	Val  int
	Next *ListNode
}

func sortedListToBST(head *ListNode) *TreeNode {
	length := 0
	for node := head; node != nil; node = node.Next {
		length++
	}

	var buildBST func(int, int) *TreeNode
	buildBST = func(start int, end int) *TreeNode {
		if start > end {
			return nil
		}
		mid := (start + end) >> 1
		root := TreeNode{}

		root.Left = buildBST(start, mid-1)
		root.Val = head.Val
		head = head.Next
		root.Right = buildBST(mid+1, end)

		return &root
	}
	return buildBST(0, length-1)
}
