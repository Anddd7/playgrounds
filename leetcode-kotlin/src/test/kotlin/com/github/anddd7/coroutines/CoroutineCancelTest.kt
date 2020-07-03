package com.github.anddd7.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class CoroutineCancelTest {
  @Test
  fun `cancel scope will cancel all coroutines`() {
    runBlocking {
      val scope = CoroutineScope(Job())
      repeat(5) {
        scope.launch {
          // delay is cancelable
          delay(10)
          println("task $it")
        }
      }
      scope.cancel()
      delay(100)
    }
  }

  @Test
  fun `only cancel target job`() {
    runBlocking {
      val scope = CoroutineScope(Job())
      val jobs = (0 until 5).map {
        scope.launch {
          // delay is cancelable
          delay(10)
          println("task $it")
        }
      }
      jobs[3].cancel()
      delay(100)
    }
  }

  @Test
  fun `check coroutine state by your self`() {
    runBlocking {
      val scope = CoroutineScope(Job())
      val job = scope.launch {
        var it = 0
        while (true) {
          ensureActive()
          println("task ${it++}")
          delay(10)
        }
      }
      delay(30)
      job.cancel()
      delay(100)
    }
  }
}
