package com.github.anddd7.coroutine

import com.github.anddd7.coroutine.CoroutineUtils.log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object CoroutineBasics {
    private const val delayed = 200L

    /**
     * launch is a coroutine builder.
     * | launches a new coroutine concurrently with the rest of the code, which continues to work independently
     * | 启动一个新的协程独立运行, 主线程会继续执行剩下的代码
     *
     * delay is a special suspending function.
     *
     * runBlocking is also a coroutine builder
     * | that bridges the non-coroutine world of a regular fun main() and the code with coroutines inside of runBlocking { ... } curly braces.
     * | 连通非协程世界的函数
     */
    fun first() = runBlocking {
        launch {
            delay(delayed)
            println("World!")
        }
        println("Hello, ")
    }

    fun extractFunction() {
        suspend fun doWorld() {
            delay(delayed)
            println("World!")
        }

        runBlocking {
            launch {
                doWorld()
            }
            println("Hello, ")
        }
    }

    fun newCoroutineScope() {
        suspend fun doWorld() = coroutineScope {
            launch {
                delay(delayed)
                println("World!")
            }
            println("Hello")
        }

        runBlocking {
            doWorld()
        }
    }

    fun scopeBuilder() {
        // coroutine scope will wait until all the launched coroutines are finished
        suspend fun doWorld() = coroutineScope {
            // 并行launch
            launch {
                delay(2000L)
                println("World 2")
            }
            launch {
                delay(1000L)
                println("World 1")
            }
            println("Hello")
        }

        runBlocking {
            doWorld()
            println("Done")
        }
    }

    fun mixedScopes() {
        suspend fun doWorld(string: String) = coroutineScope {
            CoroutineUtils.randomDelay(delayed)
            log(string)
        }

        runBlocking {
            (1..5).forEach {
                // 独立执行
                launch {
                    doWorld("finished launch under run blocking + $it")
                }
            }
            log("finished of launch under run blocking")

            coroutineScope {
                (1..5).forEach {
                    launch {
                        doWorld("finished launch under coroutine scope A + $it")
                    }
                }
                log("end of coroutine scope A")
            }
            log("finished of coroutine scope A")

            // 需要等待上一个coroutine scope执行完毕
            coroutineScope {
                (1..5).forEach {
                    launch {
                        doWorld("finished launch under coroutine scope B + $it")
                    }
                }
                log("end of coroutine scope B")
            }
            log("finished of coroutine scope B")

            coroutineScope {
                coroutineScope {
                    (1..5).forEach {
                        launch {
                            doWorld("finished launch under coroutine scope C-X1 + $it")
                        }
                    }
                    log("end of coroutine scope C-X1")
                }
                log("finished of coroutine scope C-X1")

                coroutineScope {
                    (1..5).forEach {
                        launch {
                            doWorld("finished launch under coroutine scope C-X2 + $it")
                        }
                    }
                    log("end of coroutine scope C-X2")
                }
                log("finished of coroutine scope C-X2")

                log("end of coroutine scope C")
            }

            log("end of run blocking")
        }

        log("finished run blocking")
    }

    fun explicitJob() {
        runBlocking {
            val job = launch {
                delay(1000L)
                println("World!")
            }
            // 立即打印
            println("Hello")
            job.join() // wait until child coroutine completes
            println("Done")
        }
    }

    fun powerOfCoroutine() = runBlocking {
        repeat(100_000) {
            launch {
                delay(5000L)
                print(".")
            }
        }
    }
}
