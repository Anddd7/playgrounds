package com.github.anddd7.leetcode

import org.junit.Test

class SolutionTest {
  @Test
  fun test() {
    val listOf = listOf(0, 1, 2, 4, 2, 1)

    val printKMoves = Solution.findInMountainArray(
        3,
        object : Solution.MountainArray {
          override fun get(index: Int) = listOf[index]
          override fun length() = listOf.size
        }
    )
    println(printKMoves)
  }
}
