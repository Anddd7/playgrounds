package com.github.anddd7.coroutine

import org.junit.jupiter.api.Test

internal class CoroutineBasicsTest {
    @Test
    fun `run first`() {
        CoroutineBasics.first()
    }

    @Test
    fun `run extracted function`() {
        CoroutineBasics.extractFunction()
    }

    @Test
    fun `run new coroutine scope`() {
        CoroutineBasics.newCoroutineScope()
    }

    @Test
    fun `run scope builder`() {
        CoroutineBasics.scopeBuilder()
    }

    @Test
    fun `run new coroutine scope with nested scopes`() {
        CoroutineBasics.mixedScopes()
    }

    @Test
    fun `run explicit job`() {
        CoroutineBasics.explicitJob()
    }

    @Test
    fun `get the power of the coroutine`() {
        CoroutineBasics.powerOfCoroutine()
    }
}
