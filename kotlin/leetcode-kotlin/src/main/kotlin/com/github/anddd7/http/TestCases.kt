@file:OptIn(DelicateCoroutinesApi::class, ExperimentalTime::class)

package com.github.anddd7.http

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import java.net.URI
import kotlin.random.Random
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

fun main() {
    concurrentRun(
        HttpClientTool::token,
    )
}

private fun concurrentRun(fn: HttpClientTool.() -> Unit) {
    val times = 300
    val nThreads = 15
    val httpClientTool = HttpClientTool()

    val context = newFixedThreadPoolContext(nThreads, "fixed")
    runBlocking(context) {
        (0..times)
            .map {
                async {
                    measureTime { httpClientTool.fn() }
                }
            }
            .awaitAll()
            .joinToString(", ")
            .let(::println)
    }
}

private fun HttpClientTool.omj() {
    post(
        // URI("http://localhost:8083/api/v1/xxx/xxx-application/search"),
        URI("http://localhost:8083/api/v1/xxx/xxx-application/search"),
        """
        {   "applicationStartDate":"2020-12-31T16:00:00.000Z","applicationEndDate":"2021-12-07T16:00:00.000Z","pageNumber":0,"pageSize":50}
        """.trimIndent()
    )
}

private fun HttpClientTool.token() {
    val account = "GroomId" + Random.nextInt(0, 20)

    get(
        URI("http://localhost:8080/api/v1/test/token?type=Access&account=${account}&scope=couple"),
    )
}

