package leetcode

type Trie struct {
	next  [26]*Trie
	isEnd bool
}

func respace(dictionary []string, sentence string) int {
	rootTrie := initialTrie(dictionary)
	dp := make([]int, len(sentence)+1)

	for i := 0; i < len(sentence); i++ {
		// 如果当前字符不属于任何一个单词
		dp[i+1] = dp[i] + 1

		// 倒序匹配
		rootTrie.indexOfWordFrom(sentence, i, func(index int) {
			if dp[index] < dp[i+1] {
				dp[i+1] = dp[index]
			}
		})
	}

	return dp[len(sentence)]
}

func initialTrie(dictionary []string) Trie {
	root := Trie{
		next:  [26]*Trie{},
		isEnd: false,
	}
	for _, word := range dictionary {
		root.insert(word)
	}
	return root
}

func (this *Trie) insert(s string) {
	current := this
	for i := len(s) - 1; i >= 0; i-- {
		index := s[i] - 'a'

		if current.next[index] == nil {
			current.next[index] = &Trie{next: [26]*Trie{}}
		}

		current = current.next[index]
	}

	current.isEnd = true
}

func (this *Trie) indexOfWordFrom(s string, start int, handler func(int)) {
	current := this
	for i := start; i >= 0; i-- {
		index := s[i] - 'a'

		if current.next[index] == nil {
			break
		}
		if current.next[index].isEnd {
			handler(i)
		}

		current = current.next[index]
	}
}
