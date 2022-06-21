package com.github.anddd7.leetcode

object Solution {
  interface MountainArray {
    fun get(index: Int): Int
    fun length(): Int
  }

  fun findInMountainArray(target: Int, mountainArr: MountainArray): Int {
    val top = findTop(mountainArr, 0, mountainArr.length() - 1)
    val max = mountainArr.get(top)
    if (max == target) return top
    if (max < target) return -1

    return findNumber(mountainArr, 0, top - 1, target, false)
        ?: findNumber(mountainArr, top + 1, mountainArr.length() - 1, target, true)
        ?: -1
  }

  private fun findNumber(mountainArr: MountainArray, start: Int, end: Int, target: Int, desc: Boolean): Int? {
    if (start > end) return null
    if (start == end) return start.takeIf { mountainArr.get(it) == target }
    val midIndex = (start + end) / 2
    val midValue = mountainArr.get(midIndex)
    if (midValue == target) return midIndex

    return if (desc xor (midValue < target))
      findNumber(mountainArr, midIndex + 1, end, target, desc)
    else
      findNumber(mountainArr, start, midIndex - 1, target, desc)
  }

  fun findTop(mountainArr: MountainArray, start: Int, end: Int): Int {
    if (start == end) return start
    val a = (start + end) / 2
    val b = a + 1

    if (mountainArr.get(a) > mountainArr.get(b)) {
      return findTop(mountainArr, start, a)
    }

    return findTop(mountainArr, b, end)
  }
}
