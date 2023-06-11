package com.test.myapplication.extensions

import kotlinx.coroutines.CancellationException

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */

/**
 * We should rethrow this if we wrap suspend call inside try catch
 */
fun Throwable.rethrowCancellationException() {
    if (this is CancellationException) {
        throw this
    }
}

/**
 * Find cause from chain of e.cause.cause.cause....
 * Checking self too
 */
inline fun <reified T : Throwable> Throwable.hasCause(): Boolean {
    return findCause<T>() != null
}

/**
 * Find cause from chain of e.cause.cause.cause....
 * Checking self too
 *
 * @return cause T or null
 */
inline fun <reified T : Throwable> Throwable.findCause(): T? {
    var next: Throwable? = this
    while (next != null) {
        if (next is T) {
            return next
        }
        next = next.cause
    }
    return null
}
