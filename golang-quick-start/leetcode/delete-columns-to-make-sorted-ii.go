package leetcode

func minDeletionSize(A []string) int {
	return deletableColumns([][]string{A}, 0, len(A[0]))
}

func deletableColumns(groups [][]string, index int, length int) int {
	if len(groups) == 0 {
		return 0
	}
	if index == length {
		return 0
	}

	var nextGroups [][]string
	for i := range groups {
		strs := groups[i]
		for j := 1; j < len(strs); j++ {
			prev := strs[j-1][index]
			current := strs[j][index]
			if current < prev {
				return 1 + deletableColumns(groups, index+1, length)
			}
			if current == prev {
				nextGroup := []string{
					strs[j-1],
					strs[j],
				}
				for j+1 < len(strs) && strs[j+1][index] == current {
					j++
					nextGroup = append(nextGroup, strs[j])
				}
				nextGroups = append(nextGroups, nextGroup)
			}
		}
	}

	return deletableColumns(nextGroups, index+1, length)
}
