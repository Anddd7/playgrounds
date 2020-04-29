package com.github.anddd7.leetcode

object Solution {
  /**
   * Round 1
   * 通过二维数组记录动态状态
   * 0 A[0]
   * 1 A[0]|A[1] : A[1]
   * 2 A[0]|A[1]|A[2] : A[1]|A[2] : A[2]
   * ...
   * - temp[i][j] = A[j] | A[j+1] ... A[i-1] | A[i]
   * - 记录所有结果到set
   * - return set.size
   * > 内存溢出, 当n非常大的时候, 二维数组占空间太多
   *
   * Round 2
   * 只记录最近一次的结果, 一维数组即可
   * A[0] ...
   * A[0]=A[0]|A[1] : A[1] ...
   * A[0]=A[0]|A[2] : A[1]=A[1]|A[2] : A[2] ...
   * ...
   * - A[j] = A[j]|A[i]
   * > 超时, 假设:
   * > 当某一个 A[j] & A[i] = A[i] 的时候, A[i]所有的1在A[j]的对应位置也是1
   * > 因为我们使用一维数组, A[j]之前的元素都是是"包含(|)"A[j], 1只多不少
   * > 因此后续的A[0]~A[j] | A[i]时, 值不会再变化, 可以直接跳过
   * > e.g 11111  01111  00111  00101  |  00100
   * > 可以直接跳过
   *
   * Round 3
   * 增加一个跳过选项
   *
   */
  fun subarrayBitwiseORs(A: IntArray): Int {
    val result = mutableSetOf<Int>()

    for (i in A.indices) {
      result.add(A[i])

      // why reversed: 越靠左, 里面包含的操作数越多, A[0]|A[1]...|A[i] = A[0]|A[1]...A[i-1]  |  A[i]
      for (j in (0 until i).reversed()) {
        if (A[j] and A[i] == A[i]) break
        A[j] = A[j] or A[i]
        result.add(A[j])
      }
    }

    return result.size
  }
}
