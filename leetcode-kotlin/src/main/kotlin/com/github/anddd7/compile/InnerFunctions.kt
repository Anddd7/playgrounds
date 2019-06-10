package com.github.anddd7.compile

@NeedImprove
fun case1(a: Int): Int {
    // 会生成多个方法
    fun add(b: Int) = a + b
    return add(1)
}

@Good
fun case2(a: Int): Int {
    // 少生成一个function对象
    fun add(a: Int, b: Int) = a + b
    return add(a, 1)
}

// 等于 add(a,b)
fun Int.add(b: Int) = this + b

@Good
fun case3(a: Int): Int {
    return a.add(1)
}


