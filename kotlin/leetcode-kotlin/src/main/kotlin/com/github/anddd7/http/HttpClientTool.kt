package com.github.anddd7.http

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers.ofString
import java.net.http.HttpResponse
import java.time.Duration
import java.util.logging.Logger

class HttpClientTool {
    private val logger: Logger = Logger.getAnonymousLogger()

    private val client: HttpClient by lazy {
        HttpClient.newBuilder()
            .connectTimeout(Duration.ofMinutes(2))
            .build()
    }

    fun post(uri: URI, body: String) {
        HttpRequest.newBuilder(uri)
            .POST(ofString(body))
            .header("Content-Type", "application/json")
            .build().process()
    }

    fun get(uri: URI) {
        HttpRequest.newBuilder(uri)
            .GET()
            .header("Content-Type", "application/json")
            .build()
            .process()
    }

    private fun HttpRequest.process() {
        val response = client.send(this, HttpResponse.BodyHandlers.ofString())

        logger.info(
            """
                response status: ${response.statusCode()}
                response body: ${response.body()}
            """.trimIndent()
        )
    }
}

