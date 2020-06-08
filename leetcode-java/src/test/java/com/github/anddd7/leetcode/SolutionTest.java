package com.github.anddd7.leetcode;

class SolutionTest {

  public static void main(String[] args) {
    System.out.println(
        Solution.INSTANCE.equationsPossible(new String[]{
                "a==b", "e==c", "b==c", "a!=e"
            }
        )
    );
  }
}
