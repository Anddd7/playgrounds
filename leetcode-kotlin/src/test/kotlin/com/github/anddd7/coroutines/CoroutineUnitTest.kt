package com.github.anddd7.coroutines

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class CoroutineUnitTest {
    private val coroutineScope = TestCoroutineScope()

    @BeforeEach
    internal fun setUp() {

    }

    @AfterEach
    internal fun tearDown() {
        coroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun `suspend method has been invoked`() {
        val emailService = mockk<EmailService>()
        val applicationService = ApplicationService(coroutineScope, emailService)

        coEvery { emailService.sendEmail() } returns Unit

        coroutineScope.runBlockingTest {
            applicationService.save()
        }

        coVerify(exactly = 1) {
            emailService.sendEmail()
        }
    }
}

class ApplicationService(
    private val coroutineScope: CoroutineScope,
    private val emailService: EmailService
) {
    fun save() {
        println("saving the application")
        coroutineScope.launch {
            println("waiting to send email")
            delay(100000)
            emailService.sendEmail()
            println("sending email successfully")
        }
        println("saved the application")
    }
}

interface EmailService {
    suspend fun sendEmail()
}
