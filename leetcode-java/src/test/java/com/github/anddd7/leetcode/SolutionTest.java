package com.github.anddd7.leetcode;

import java.util.Arrays;

class SolutionTest {

  public static void main(String[] args) {
    System.out.println(
        Solution.INSTANCE.peopleIndexes(
            Arrays.asList(
                Arrays.asList("leetcode", "google", "facebook"),
                Arrays.asList("google", "microsoft"),
                Arrays.asList("google", "facebook"),
                Arrays.asList("google"),
                Arrays.asList("amazon")
            )
        )
    );
  }
}
