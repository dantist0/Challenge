package com.test.myapplication.utils

import com.test.myapplication.extensions.hasCause
import com.test.myapplication.extensions.rethrowCancellationException
import kotlinx.coroutines.delay
import java.io.IOException

/**
 * Repeats block with [interval], if io error happens
 *
 * @author Alexey Kuznetsov 10.06.2023.
 */
suspend fun <T> repeatIfIOError(
    interval: Long = 500L,
    doOnRepeat: (Throwable) -> Unit = {},
    doOnSuccess: () -> Unit = {},
    block: suspend () -> T,
): T {
    var result: T
    while (true) {
        try {
            result = block()
            break
        } catch (e: Throwable) {
            e.rethrowCancellationException()
            if (e.hasCause<IOException>()) {
                delay(interval)
                doOnRepeat(e)
            } else {
                throw e
            }
        }
    }
    doOnSuccess()
    return result
}