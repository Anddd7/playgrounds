package leetcode

func isSubsequence(s string, t string) bool {
	spos := 0
	tpos := 0

	for spos < len(s) && tpos < len(t) {
		if s[spos] == t[tpos] {
			spos++
		}
		tpos++
	}

	return spos == len(s)
}
