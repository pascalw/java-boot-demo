package nl.kabisa.util

import java.util.function.Function

class Result<T, E : Throwable> {

    val result: T?
    val exception: E?

    private constructor(result: T) {
        this.result = result
        this.exception = null
    }

    private constructor(exception: E) {
        this.exception = exception
        this.result = null
    }

    val isOk: Boolean
        get() = !isErr

    val isErr: Boolean
        get() = exception != null

    fun <U> map(mapper: Function<T, U>): Result<U, E> {
        if (isErr)
            return Result(exception!!)
        else
            return Result(mapper.apply(result!!))
    }

    fun unwrap(): T {
        if (isErr)
            throw exception!!

        return result!!
    }

    companion object {
        fun <T, E : Exception> ok(result: T): Result<T, E> {
            return Result(result)
        }

        fun <T, E : Exception> err(exception: E): Result<T, E> {
            return Result(exception)
        }
    }
}
