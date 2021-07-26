package com.github.anddd7.totp

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.HmacAlgorithms
import org.apache.commons.codec.digest.HmacUtils
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.experimental.and
import kotlin.math.pow

class TOTP(
    /**
     * expiration time (seconds), default is 1 day
     */
    private val expiration: Long = 24 * 3600,
    /**
     * length of output digits, default is 6
     */
    digitsLength: Int = 6,
    private val debugEnabled: Boolean = false
) {
    private val hotp = HOTP(digitsLength, debugEnabled)
    private fun totp(k: String, t: Long): String {
        val counterOfTime = t / expiration

        log(
            """
                TOTP
                -------------------------------------------------
                ** Expiration Time: $expiration
                ** Counter of time: $counterOfTime
            """.trimIndent()
        )

        return hotp.eval(k, counterOfTime.toString())
    }

    private fun log(msg: String) {
        if (debugEnabled) println(msg)
    }

    fun eval(key: String, time: LocalDateTime): String = totp(key, time.toEpochSecond(ZoneOffset.UTC))
}

class HOTP(
    private val digitsLength: Int = 6,
    private val debugEnabled: Boolean = false
) {
    private fun hotp(k: String, c: String): String {
        // 1. HS = HMAC-SHA-1(K,C)
        val hs = HmacUtils(HmacAlgorithms.HMAC_SHA_1, k).hmac(c)
        // 2. Sbits = DT(HS)
        val sbits = dt(hs)
        // 3. Snum, hex array to decimal array
        val snum = sbits.map { it.toInt() and 0xff }.reduce { acc, i -> acc * 10 + i }
        // 4. D
        val mask = 10.0.pow(digitsLength.toDouble()).toInt()
        val D = snum % mask

        log(
            """
                HOTP
                -------------------------------------------------
                hs (Hex)    : ${Hex.encodeHex(hs)}
                hs (Binary) : ${hs.map { it.toInt() and 0xff }}
            """.trimIndent()
        )

        return D.toString().padStart(digitsLength, '0')
    }

    /**
     * dynamic truncation
     */
    private fun dt(hs: ByteArray): ByteArray {
        // 2.1 get offset from last 4 bit
        val offset = hs.last().and(0xf).toInt()
        // 2.2 get sbits
        val sbits = hs.copyOfRange(offset, offset + 4)

        log(
            """
                Dynamic Truncation
                -------------------------------------------------
                offset          : $offset
                sbits (Hex)     : ${Hex.encodeHex(sbits)}
                sbits (Binary)  : ${sbits.map { it.toInt() and 0xff }}
            """.trimIndent()
        )
        return sbits
    }

    private fun log(msg: String) {
        if (debugEnabled) println(msg)
    }

    fun eval(key: String, counter: String): String = hotp(key, counter)
}

fun main() {
    println(
        HOTP().eval("user_name", "12345678")
    )
}

