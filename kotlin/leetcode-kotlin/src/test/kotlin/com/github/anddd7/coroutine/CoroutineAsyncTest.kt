package com.github.anddd7.coroutine

import org.junit.jupiter.api.Test

internal class CoroutineAsyncTest {
    @Test
    fun `should cancel other async task when parent has been cancelled`() {
        CoroutineAsync.autoCancel()
    }
}
