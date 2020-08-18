package leetcode

func canFinish(numCourses int, prerequisites [][]int) bool {
	edges := make([][]int, numCourses)
	indegs := make([]int, numCourses)
	result := []int{}

	for _, prerequisite := range prerequisites {
		edges[prerequisite[1]] = append(edges[prerequisite[1]], prerequisite[0])
		indegs[prerequisite[0]]++
	}

	zeroIndegGroup := []int{}
	for i := 0; i < numCourses; i++ {
		if indegs[i] == 0 {
			zeroIndegGroup = append(zeroIndegGroup, i)
		}
	}

	for len(zeroIndegGroup) > 0 {
		nextGroup := []int{}
		for _, start := range zeroIndegGroup {
			for _, end := range edges[start] {
				indegs[end]--
				if indegs[end] == 0 {
					nextGroup = append(nextGroup, end)
				}
			}
			result = append(result, start)
		}
		zeroIndegGroup = nextGroup
	}

	return len(result) == numCourses
}
