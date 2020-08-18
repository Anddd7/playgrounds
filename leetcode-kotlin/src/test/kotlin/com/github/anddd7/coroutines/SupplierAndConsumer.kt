package com.github.anddd7.coroutines

import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread
import kotlin.random.Random

class SupplierAndConsumer {


  private val queue = LinkedBlockingQueue<Int>()

  fun consumer() {
    while (true) {
      println("receive :${queue.poll()}")
    }
  }

  fun supplier() {
    val random = Random(System.currentTimeMillis())
    while (true) {
      queue.offer(random.nextInt())
    }
  }

  @Test
  fun `thread test`() {
    val threadOfConsumer = thread { consumer() }
    val threadOfSupplier = thread { supplier() }

    threadOfConsumer.join(5 * 1000)
    threadOfSupplier.join(5 * 1000)
  }

  private fun supplierCallback() {
    val random = Random(System.currentTimeMillis())
    val consumer: (Int) -> Unit = {
      println("receive :$it")
    }
    while (true) {
      consumer(random.nextInt())
    }
  }

  @Test
  fun `callback test`() {
    val threadOfSupplier = thread { supplierCallback() }

    threadOfSupplier.join(5 * 1000)
  }

  private val channel = Channel<Int>()

  private suspend fun coConsumer() {
    while (true) {
      println("receive :${channel.receive()}")
    }
  }

  private suspend fun coSupplier() {
    val random = Random(System.currentTimeMillis())
    while (true) {
      channel.send(random.nextInt())
    }
  }

  @Test
  fun `coroutine test`() {
    runBlocking {
      launch { coConsumer() }
      launch { coSupplier() }

      delay(5 * 1000)
      cancel()
    }
  }
}
