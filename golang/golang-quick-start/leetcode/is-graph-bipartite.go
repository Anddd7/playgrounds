package leetcode

func isBipartite(graph [][]int) bool {
	length := len(graph)
	colors := make([]int, length)

	for i := 0; i < length; i++ {
		if colors[i] == 0 {
			if !markColor(graph, i, colors, 1) {
				return false
			}
		}
	}

	return true
}

func markColor(graph [][]int, i int, colors []int, color int) bool {
	if colors[i] == 0 {
		colors[i] = color
	} else {
		return colors[i] == color
	}

	for _, target := range graph[i] {
		if !markColor(graph, target, colors, -color) {
			return false
		}
	}

	return true
}
