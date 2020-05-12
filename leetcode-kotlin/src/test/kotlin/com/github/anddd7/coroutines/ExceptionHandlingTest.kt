package com.github.anddd7.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Test

class ExceptionHandlingTest {

  @Test
  fun `throw exception from launch in context`() {
    runBlocking {
      try {
        launchWithError()
      } catch (e: RuntimeException) {
        println("Got an error: $e")
      }
      launch { println("other task-1") }
      launch { println("other task-2") }
    }
  }

  private suspend fun launchWithError() = withContext(Dispatchers.Default) {
    launch {
      println("starting")
      throw RuntimeException("error")
      println("Untouchable")
    }
  }

  @Test
  fun `catch exception inside of launch in context`() {
    runBlocking {
      launchWithInnerError()
      launch { println("other task-1") }
      launch { println("other task-2") }
    }
  }

  private suspend fun launchWithInnerError() = withContext(Dispatchers.Default) {
    launch {
      println("starting")
      try {
        throw RuntimeException("error")
      } catch (e: RuntimeException) {
        println("Got an error: $e")
      }
      println("Untouchable")
    }
  }

  @Test
  fun `modify data in with context`() {
    var int = 0
    runBlocking {
      withContext(Dispatchers.Default) {
        for (it in 0..5) {
          launch {
            try {
              if (int == 3) {
                throw RuntimeException("error")
              }
              int++
            } catch (e: RuntimeException) {
              println("Got an error: $e")
            }
          }
        }
      }
    }
    println("finally$int")
  }
}
