package com.github.anddd7.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

internal class GlobalLaunchAndRunBlockingTest {

    private val repeatTimes = 100_000
    private val delayTime = 1000L

    @Test
    fun `should return 0 when main thread is not blocked`() {
        val number = AtomicInteger()
        repeat(repeatTimes) {
            GlobalScope.launch {
                delay(delayTime)
                number.incrementAndGet()
            }
        }

        println(number)
    }

    @Test
    fun `should return incorrect number when main thread is blocked 1s`() {
        val number = AtomicInteger()
        repeat(repeatTimes) {
            GlobalScope.launch {
                delay(delayTime)
                number.incrementAndGet()
            }
        }

        Thread.sleep(delayTime)
        println(number)
    }

    @Test
    fun `should return incorrect number when main thread is blocked by delay in runBlocking`() {
        val number = AtomicInteger()
        repeat(repeatTimes) {
            GlobalScope.launch {
                delay(delayTime)
                number.incrementAndGet()
            }
        }

        runBlocking {
            delay(delayTime)
        }
        println(number)
    }

    @Test
    fun `should return correct number when main thread is waiting for joined jobs`() {
        val number = AtomicInteger()

        runBlocking {
            val jobs = (1..repeatTimes).map {
                GlobalScope.launch {
                    delay(delayTime)
                    number.incrementAndGet()
                }
            }
            jobs.forEach { it.join() }
        }

        println(number)
    }
}
