package com.github.anddd7.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

internal class CoroutineScopeTest {

    private val repeatTimes = 100_000
    private val delayTime = 1000L

    @Test
    fun `should return correct number when main thread is waiting for inner jobs in scope`() {
        val number = AtomicInteger()

        runBlocking {
            (1..repeatTimes).map {
                launch {
                    delay(delayTime)
                    number.incrementAndGet()
                }
            }
        }

        println(number)
    }

    @Test
    fun `should return correct number when execute jobs in coroutine scope`() {
        val number = AtomicInteger()

        runBlocking {
            // 1
            println("Processing job: $number")

            // 5 异步执行
            launch {
                increaseJob(number, 1000, "Job")
            }

            // 2
            executeIncreaseJob(number, 500, "Sub-Job-1")

            // 3
            executeIncreaseJob(number, 100, "Sub-Job-2")

            // 4
            println("Waiting job~")
        }

        // 等待整个runBlocking完成
        println("Finished: $number")
    }

    private suspend fun executeIncreaseJob(number: AtomicInteger, delay: Long, name: String) =
        // 会等待异步执行的3完成再返回
        coroutineScope {
            // 1
            println("Processing $name: $number")
            // 3 异步执行
            launch {
                increaseJob(number, delay, name)
            }
            // 2
            println("Waiting $name ~")
        }

    private suspend fun increaseJob(number: AtomicInteger, delay: Long, name: String) {
        println("Execute $name")
        delay(delay)
        println("Finished $name: " + number.incrementAndGet())
    }
}
