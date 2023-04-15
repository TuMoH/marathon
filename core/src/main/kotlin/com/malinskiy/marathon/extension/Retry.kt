package com.malinskiy.marathon.extension

import com.malinskiy.marathon.log.MarathonLogging

private val logger = MarathonLogging.logger("Retry")

suspend fun <T> withRetryOrNull(retryCount: Int, block: suspend () -> T): T? {
    repeat(retryCount + 1) {
        try {
            return block()
        } catch (e: Exception) {
            logger.debug(e) { "retry: ${it + 1}" }
        }
    }
    return null
}
