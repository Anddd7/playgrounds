package main

import (
	"fmt"
)

func main() {
	fmt.Println(rearrangeCharacters("abbaccaddaeea", "aaaaa"))
}

func rearrangeCharacters(s string, target string) int {
	counterS, counterT := [26]int{}, [26]int{}

	for _, i := range s {
		counterS[int(i-'a')]++
	}
	for _, i := range target {
		counterT[int(i-'a')]++
	}
	ans := len(s)
	for i := 0; i < 26; i++ {
		if counterT[i] != 0 {
			c := counterS[i] / counterT[i]
			ans = min(ans, c)
		}
	}
	if ans == len(s) {
		return 0
	}
	return ans
}

func min(a, b int) int {
	if a > b {
		return b
	}
	return a
}
