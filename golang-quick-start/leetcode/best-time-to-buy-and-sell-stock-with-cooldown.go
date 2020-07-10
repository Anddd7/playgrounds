package leetcode

func maxProfit(prices []int) int {
	dpStay, dpJustSelled, dpAlreadySelled := 0, 0, 0

	for i, v := range prices {
		if i == 0 {
			dpStay = -v
			continue
		}

		stay := maxOf(dpStay, dpAlreadySelled-v)
		justSelled := dpStay + v
		alreadySelled := maxOf(dpJustSelled, dpAlreadySelled)

		println(stay, justSelled, alreadySelled)

		dpStay = stay
		dpJustSelled = justSelled
		dpAlreadySelled = alreadySelled
	}

	return maxOf(dpJustSelled, dpAlreadySelled)
}

func maxOf(a int, b int) int {
	if a > b {
		return a
	}
	return b
}
