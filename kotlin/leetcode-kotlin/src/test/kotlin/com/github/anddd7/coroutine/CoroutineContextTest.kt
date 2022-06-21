package com.github.anddd7.coroutine

import org.junit.jupiter.api.Test

internal class CoroutineContextTest {
    @Test
    fun `test coroutine context`() {
        CoroutineContext.allContext()
    }

    @Test
    fun `test unconfined context`() {
        CoroutineContext.unconfined()
    }

    @Test
    fun `test jumping context`() {
        CoroutineContext.jumping()
    }

    @Test
    fun `test job switching`() {
        CoroutineContext.jobSwitching()
    }
}
