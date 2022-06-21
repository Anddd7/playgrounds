package com.github.anddd7.compile

import org.junit.jupiter.api.Test

internal class JacocoIncorrectBranchTest {
    private val kt = JacocoKotlinIncorrectBranch()
    private val java = JacocoJavaIncorrectBranch()

    @Test
    fun `double safe call operator`() {
        kt.double_safe_call_operator("test")
        kt.double_safe_call_operator(null)

        java.double_safe_call_operator("test")
        java.double_safe_call_operator(null)
    }

    @Test
    fun `one safe call operator`() {
        kt.one_safe_call_operator("test")
        kt.one_safe_call_operator(null)

        java.one_safe_call_operator("test")
        java.one_safe_call_operator(null)
    }
}
