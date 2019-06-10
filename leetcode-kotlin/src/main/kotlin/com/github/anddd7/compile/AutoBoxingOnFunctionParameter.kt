package com.github.anddd7.compile

/**
 * object会编译成一个单例对象
 */
object AutoBoxingOnFunctionParameter {

    /**
     * 单例对象中的方法
     */
    fun case1(i: Int) = i + 1

    /**
     * public final static
     */
    @JvmStatic
    fun case2(i: Int) = i + 1

    /**
     * `() -> Int`被编译成一个Function对象, 返回的是一个Object
     * 得到结果后进行了复杂的装箱`((Number)i.invoke()).intValue() + 1`
     *
     * Java中使用了IntFunction等接口来避免装箱
     */
    @NeedImprove
    fun case3(i: () -> Int) = i() + 1

    fun test3() = case3 { 1 }

    /**
     * inline会将函数体内联到调用的地方
     */
    @Good
    inline fun case4(i: () -> Int) = i() + 1

    /**
     * int $i$f$case4 = false;
     * int var3 = false;
     * return 1 + 1;
     */
    fun test4() = case4 { 1 }
}
