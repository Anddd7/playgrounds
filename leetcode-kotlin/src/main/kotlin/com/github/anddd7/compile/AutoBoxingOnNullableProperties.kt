package com.github.anddd7.compile

object AutoBoxingOnNullableProperties {
    @NeedImprove
    val one: Int? = 1 // compile to Integer, default null
    @Good
    val two: Int = 2 // compile to int=2

    @NeedImprove
    val arrayOfInt: Array<Int> = arrayOf(1) // Integer[]
    @Good
    val intArray: IntArray = intArrayOf(1) // int[]
}
