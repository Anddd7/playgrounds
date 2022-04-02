package com.github.anddd7.coroutine

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

object CoroutineAsync {
    fun global() {
        val time = measureTimeMillis {
            // we can initiate async actions outside of a coroutine
            val one = getOne()
            val two = getTwo()
            // but waiting for a result must involve either suspending or blocking.
            // here we use `runBlocking { ... }` to block the main thread while waiting for the result
            runBlocking {
                println("The answer is ${one.await() + two.await()}")
            }
        }
        println("Completed in $time ms")
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getTwo() = GlobalScope.async {
        delay(1000L)
        "two"
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getOne() = GlobalScope.async {
        delay(1000L)
        "one"
    }

    fun autoCancel() = runBlocking {
        try {
            failedConcurrentSum()
        } catch (e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }

    private suspend fun failedConcurrentSum(): Int = coroutineScope {
        val one = async<Int> {
            try {
                delay(Long.MAX_VALUE) // Emulates very long computation
                42
            } finally {
                println("First child was cancelled")
            }
        }
        val two = async<Int> {
            println("Second child throws an exception")
            throw ArithmeticException()
        }
        one.await() + two.await()
    }
}
