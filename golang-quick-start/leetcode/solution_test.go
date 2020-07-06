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
	inputs := []string{
		"vqyoysnpxbjiitandmvugsqpfmggkv",
		"uzdfeclxepjzfecmsxrqqkcomtrnvm",
		"yvhwrsapfffwehdmvqwxstgeexfeua",
		"awjymwysjpazpgdeqtvdiebfwuapin",
		"odhihlbvsnximvdwqntdeqptigiyik",
		"qtrfpwiilxskcieilfvarqbnpdxham",
		"whvrqkdwuzbcaagsmlfvfbeataygud",
		"kncwqrmejjmhtfhppsrdmzqperwlww",
		"hgphuwaumjjibzhvvejpniopjxizie",
		"bxvccswqevnudqicgrvjecfqpeppob",
		"nnmvncnpbksdjyjjelsjizliicxpgz",
		"oifmofrkbgpxlhkcbibwaoiygmqqio",
		"ekdfyvsumngcfjlydgpmhgjjyfovfi",
		"fyqryrpkvauhkylmfzhuasjxpqrohx",
		"rdvjglvpavzdmtobnpjfwdwivhrpsj",
		"zahrkuiejecndfprwysunznialtfok",
		"jlrgpfdptlolmlqoophhciiqjnxdkh",
		"bhbsdukebqvvemrcunboipprcbrfcl",
		"kreyeyvsmufolvsrzdyeqpuqlieeij",
		"vgosaxsfnbsndstjohgyknyionhoga",
		"igmnlibpadandgtugbgxpxwlqbknmv",
		"mjdbxxprxbjegvtthlrenhfpdlamww",
		"qfssehellhvqyntozbrizixptppfpr",
		"utghfndlcturahtcvmqrjyxqfhrsxt",
		"xvminqhybbiadetniqfwubqxmjokjv",
		"udfckncwvhcrmxtbkqbqqptymlqnss",
		"gwwcmterazvyakuvwtyhthfiohlywq",
		"mpieryurvarojfvhfbbcwepdeoedri",
		"lpaonsugmlzuweyvrrlgwwdjsgwmoh",
		"kexyawgkinwvjvzwvofqlthmhaicgs",
	}

	result := minDeletionSize(inputs)

	fmt.Println(result)
}
