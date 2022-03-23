package com.github.anddd7.coroutine

import kotlinx.coroutines.delay
import kotlin.random.Random

object CoroutineUtils {
    fun log(message: String) {
        println("[${Thread.currentThread().name}]: \t $message")
    }

    suspend fun randomDelay(delayTime: Long = 1000) {
        val random = Random.nextLong(delayTime shr 1, delayTime)
        log("Suspending for $random ms")
        delay(random)
    }
}
