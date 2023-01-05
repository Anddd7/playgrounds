package main

import (
	"fmt"
)

func main() {
	fmt.Println(countPairs([]int{1, 4, 2, 7}, 2, 6))
}

type trie struct {
	count    int
	children [2]*trie
}

const HIGH_BIT = 14

func countPairs(nums []int, low int, high int) (count int) {
	root := &trie{}
	for _, num := range nums {
		count += root.search(num, high+1) - root.search(num, low)
		root.insert(num)
	}
	return count
}

func (t *trie) insert(number int) {
	cur := t
	for k := HIGH_BIT; k >= 0; k-- {
		v := (number >> k) & 1
		if cur.children[v] == nil {
			cur.children[v] = &trie{}
		}
		cur = cur.children[v]
		cur.count++
	}
}

func (t *trie) search(number, x int) (count int) {
	cur := t
	for k := HIGH_BIT; k >= 0 && cur != nil; k-- {
		v := (number >> k) & 1
		if (x>>k)&1 == 1 {
			// x 当前位为 1，只要 number 和 目标 的异或结果为 0，就可以直接计数
			if cur.children[v] != nil {
				count += cur.children[v].count
			}
			// 然后继续搜索异或结果为 1 的，是否在下一位满足小于要求
			cur = cur.children[v^1]
		} else {
			// x 当前位为 0，number 和 目标的异或结果也只能为 0，是否在下一位满足小于要求
			cur = cur.children[v]
		}
	}
	return count
}
