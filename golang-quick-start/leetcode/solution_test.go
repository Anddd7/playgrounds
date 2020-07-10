package leetcode

import (
	"fmt"
	"testing"
)

func Test_uniquePathsWithObstacles(t *testing.T) {
	inputs := [][]int{
		{0, 0, 0},
		{0, 1, 0},
		{0, 0, 0},
	}
	// inputs := [][]int{
	// 	{1},
	// }

	// result := uniquePathsWithObstacles(inputs)
	result := uniquePathsWithObstacles_optimize(inputs)

	fmt.Println(result)
}

func Test_minDeletionSize(t *testing.T) {
	inputs := []string{"vqyoysnpxbjiitandmvugsqpfmggkv", "uzdfeclxepjzfecmsxrqqkcomtrnvm", "yvhwrsapfffwehdmvqwxstgeexfeua", "awjymwysjpazpgdeqtvdiebfwuapin", "odhihlbvsnximvdwqntdeqptigiyik", "qtrfpwiilxskcieilfvarqbnpdxham", "whvrqkdwuzbcaagsmlfvfbeataygud", "kncwqrmejjmhtfhppsrdmzqperwlww", "hgphuwaumjjibzhvvejpniopjxizie", "bxvccswqevnudqicgrvjecfqpeppob", "nnmvncnpbksdjyjjelsjizliicxpgz", "oifmofrkbgpxlhkcbibwaoiygmqqio", "ekdfyvsumngcfjlydgpmhgjjyfovfi", "fyqryrpkvauhkylmfzhuasjxpqrohx", "rdvjglvpavzdmtobnpjfwdwivhrpsj", "zahrkuiejecndfprwysunznialtfok", "jlrgpfdptlolmlqoophhciiqjnxdkh", "bhbsdukebqvvemrcunboipprcbrfcl", "kreyeyvsmufolvsrzdyeqpuqlieeij", "vgosaxsfnbsndstjohgyknyionhoga", "igmnlibpadandgtugbgxpxwlqbknmv", "mjdbxxprxbjegvtthlrenhfpdlamww", "qfssehellhvqyntozbrizixptppfpr", "utghfndlcturahtcvmqrjyxqfhrsxt", "xvminqhybbiadetniqfwubqxmjokjv", "udfckncwvhcrmxtbkqbqqptymlqnss", "gwwcmterazvyakuvwtyhthfiohlywq", "mpieryurvarojfvhfbbcwepdeoedri", "lpaonsugmlzuweyvrrlgwwdjsgwmoh", "kexyawgkinwvjvzwvofqlthmhaicgs"}

	result := minDeletionSize(inputs)

	fmt.Println(result)
}

func Test_hasPathSum(t *testing.T) {
	// inputs := &TreeNode{
	// 	5,
	// 	&TreeNode{
	// 		4,
	// 		&TreeNode{11,
	// 			&TreeNode{7, nil, nil},
	// 			&TreeNode{2, nil, nil},
	// 		},
	// 		nil,
	// 	},
	// 	&TreeNode{
	// 		8,
	// 		&TreeNode{13, nil, nil},
	// 		&TreeNode{4, nil, &TreeNode{1, nil, nil}},
	// 	},
	// }
	inputs := &TreeNode{
		-2,
		nil,
		&TreeNode{-3, nil, nil},
	}

	result := hasPathSum(inputs, -2)

	fmt.Println(result)
}

func Test_thirdMax(t *testing.T) {
	inputs := []int{
		1, 2, -2147483648,
	}

	result := thirdMax(inputs)

	fmt.Println(result)
}

func Test_divingBoard(t *testing.T) {
	result := divingBoard(1, 2, 3)

	fmt.Println(result)
}

func Test_respace(t *testing.T) {
	result := respace(
		[]string{
			"looked", "just", "like", "her", "brother",
		},
		"jesslookedjustliketimherbrother",
	)

	fmt.Println(result)
}

func Test_maxProfit(t *testing.T) {
	result := maxProfit(
		[]int{
			1, 2, 3,
		},
	)

	fmt.Println(result)
}
