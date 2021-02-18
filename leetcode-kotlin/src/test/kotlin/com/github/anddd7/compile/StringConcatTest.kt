package com.github.anddd7.compile

import org.junit.jupiter.api.RepeatedTest

internal class StringConcatTest {

    val stringConcat = StringConcat()
    val times = 1000000

    @RepeatedTest(3)
    fun stringbuilder_test() {
        for (i in 0..times) {
            stringConcat.stringBuilder("testabc")
        }
    }

    @RepeatedTest(3)
    fun regular_test() {
        for (i in 0..times) {
            stringConcat.stringBuilder("testabc")
        }
    }

    @RepeatedTest(3)
    fun inline_test() {
        for (i in 0..times) {
            stringConcat.stringBuilder("testabc")
        }
    }
}