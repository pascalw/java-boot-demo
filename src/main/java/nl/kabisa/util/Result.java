package nl.kabisa.util;

import java.util.function.Function;

public class Result<T, E extends Exception> {
    public static <T, E extends Exception> Result<T, E> ok(T result) {
        return new Result<>(result);
    }

    public static <T, E extends Exception> Result<T, E> err(E exception) {
        return new Result<>(exception);
    }

    public final T result;
    public final E exception;

    private Result(T result) {
        this.result = result;
        this.exception = null;
    }

    private Result(E exception) {
        this.exception = exception;
        this.result = null;
    }

    public boolean isOk() {
        return !isErr();
    }

    public boolean isErr() {
        return exception != null;
    }

    public <U> Result<U, E> map(Function<T, U> mapper) {
        if (isErr())
            return new Result<>(exception);
        else
            return new Result<>(mapper.apply(result));
    }

    public T unwrap() throws E {
        if (isErr())
            throw exception;

        return result;
    }
}
