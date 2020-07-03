package com.github.anddd7.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import org.junit.jupiter.api.Test

class ExceptionHandlingTest {

  /**
   * 基于Job初始化的CoroutineScope, 会传递exception
   * 遇到exception时会自动取消parent和child
   * 而且exception是通过coroutine内部传递出去的, 无法通过try-catch launch来抓取
   */
  @Test
  fun `throw exception will cancel parent and other child coroutine`() {
    runBlocking {
      val scope = CoroutineScope(Job())
      val job = scope.launch {
        repeat(5) {
          launch {
            if (it == 3) {
              println("failed")
              throw RuntimeException()
            } else {
              println("task $it")
            }
          }
          delay(10)
        }
        println("do other things")
      }
      job.join()
    }
  }

  /**
   * SupervisorJob/supervisorScope 不会引起parent和其他child的cancel
   */
  @Test
  fun `supervisor scope(job) wont expose exception`() {
    runBlocking {
      val scope = CoroutineScope(Job())
      val job = scope.launch {
        repeat(5) {
//          val subScope = CoroutineScope(SupervisorJob())
          supervisorScope {
//              subScope.launch {
            launch {
              if (it == 3) {
                println("failed")
                throw RuntimeException()
              } else {
                println("task $it")
              }
            }
          }
          delay(10)
        }
        println("do other things")
      }
      job.join()
    }
  }

  /**
   * launch 和 async 都可以直接在内部处理异常(与coroutine无关)
   */
  @Test
  fun `catch exception of async with direct way`() {
    runBlocking {
      val scope = CoroutineScope(Job())
      val deferred = scope.async {
        try {
          println("error")
          throw RuntimeException()
        } catch (e: Exception) {
          println("handle it")
        }
      }

      deferred.await()
    }
  }

  /**
   * 但我们通常使用 async 的时候, 是希望在获取结果的时候再处理异常
   * 当 async.await 出现异常时, 会传递到root job
   * 因此, 你在root job内依然可以使用try-catch捕获
   */
  @Test
  fun `catch exception of async while await it`() {
    runBlocking {
      val scope = CoroutineScope(Job())
      val value1 = scope.async {
        "value1"
      }
      val value2: Deferred<String> = scope.async {
        println("error")
        throw RuntimeException()
      }

      try {
        println(value1.await() + value2.await())
      } catch (e: Exception) {
        println("got a error: $e")
      }
    }
  }

  /**
   * 当你的 async 被包裹在launch中(也就是再低一层), 因为exception总是会被传递到root job
   * 你是无法在 launch 这一层catch到exception的
   * launch会因为async的异常而被cancel, 然后parent和其他child也会被cancel
   */
  @Test
  fun `cant catch exception of nested async while await it`() {
    runBlocking {
      val scope = CoroutineScope(Job())
      val job = scope.launch {
        val value1 = async {
          "value1"
        }
        val value2: Deferred<String> = async {
          println("error")
          throw RuntimeException()
        }

        // try-catch 无效
//        try {
        println(value1.await() + value2.await())
//        } catch (e: Exception) {
//          println("got a error: $e")
//        }
      }

      val otherJob = scope.launch {
        delay(10)
        println("other works")
      }

      delay(100)
      println(job.isCancelled)
      println(otherJob.isCancelled)
    }
  }

  @Test
  fun `catch exception of nested async with supervisor scope`() {
    runBlocking {
      val scope = CoroutineScope(Job())
      val job = scope.launch {
        supervisorScope {
          val value1 = async {
            "value1"
          }
          val value2: Deferred<String> = async {
            println("error")
            throw RuntimeException()
          }

          try {
            println(value1.await() + value2.await())
          } catch (e: Exception) {
            println("got a error: $e")
          }
        }
      }

      val otherJob = scope.launch {
        delay(10)
        println("other works")
      }

      delay(100)
      println(job.isCancelled)
      println(otherJob.isCancelled)
    }
  }
}
