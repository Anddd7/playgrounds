package com.github.anddd7.totp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

internal class TOTPTest {
    private val todayMidnight = LocalDate.now().atStartOfDay()
    private val now = LocalDateTime.now()

    private val totp = TOTP()
    private val username = "andong"
    private val otpOfToday = totp.eval(username, todayMidnight)

    @Test
    fun `should get the same otp`() {
        val t = now

        val otp = totp.eval(username, t)

        assertThat(otp).isEqualTo(otpOfToday)
    }

    @Test
    fun `should get the different otp in future time`() {
        val t = todayMidnight.plusDays(1)

        val otp = totp.eval(username, t)

        assertThat(otp).isNotEqualTo(otpOfToday)
    }

    @Test
    fun `should get the different otp in expired time`() {
        val t = todayMidnight.minusSeconds(1)

        val otp = totp.eval(username, t)

        assertThat(otp).isNotEqualTo(otpOfToday)
    }
}
