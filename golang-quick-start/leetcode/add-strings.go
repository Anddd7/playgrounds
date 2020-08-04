package leetcode

import "strconv"

func addStrings(num1 string, num2 string) string {
	result := ""
	carry := 0
	i, j := len(num1)-1, len(num2)-1
	for i >= 0 || j >= 0 || carry > 0 {
		var number1 int
		if i >= 0 {
			number1 = int(num1[i] - '0')
			i--
		}
		var number2 int
		if j >= 0 {
			number2 = int(num2[j] - '0')
			j--
		}
		sum := number1 + number2 + carry
		result = strconv.Itoa(sum%10) + result
		carry = sum / 10
	}

	return result
}
