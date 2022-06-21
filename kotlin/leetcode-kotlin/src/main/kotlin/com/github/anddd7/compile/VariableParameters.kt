package com.github.anddd7.compile

@NeedImprove
fun ints(vararg values: Int) =
    values.reduce { acc, i -> acc + i }

fun case1() {
    ints(1, 2, 3)
}

fun case2() {
    val values = intArrayOf(1, 2, 3)
    ints(*values) // 会额外多一次 Arrays.copyOf(values, values.length)
}

fun case3() {
    val values = intArrayOf(2)
    ints(1, *values, 3) // 会创建 IntSpreadBuilder 来join多个参数
}

@Good
fun betterInts(values: IntArray) =
    values.reduce { acc, i -> acc + i }

fun better() {
    val values = intArrayOf(1, 2, 3)
    betterInts(values)
}
