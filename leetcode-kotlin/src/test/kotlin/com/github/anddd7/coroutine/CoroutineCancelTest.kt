package com.github.anddd7.coroutine

import org.junit.jupiter.api.Test

internal class CoroutineCancelTest {
    @Test
    fun `should cancel the job`() {
        CoroutineCancel.cancel()
    }

    @Test
    fun `should failed to cancel and continue the job`() {
        CoroutineCancel.failed2Cancel()
    }

    @Test
    fun `should cancel the job after coroutine is inactive`() {
        CoroutineCancel.manualCancelWithStatus()
    }

    @Test
    fun `should cancel the job with yield`() {
        CoroutineCancel.manualCancelWithYield()
    }

    @Test
    fun `should cancel the job and execute finally`() {
        CoroutineCancel.closeResourceWithFinally()
    }

    @Test
    fun `should cancel the job and throw error after timeout`() {
        CoroutineCancel.timeoutCancel()
    }

    @Test
    fun `should cancel the job after timeout`() {
        CoroutineCancel.timeoutCancelWithoutError()
    }
}
