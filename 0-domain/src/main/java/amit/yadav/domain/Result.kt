package amit.yadav.domain

sealed class Result<out T> {

    @PublishedApi
    internal data class Success<out T>(val value: T) : Result<T>()

    @PublishedApi
    internal data class Failure(val exception: Throwable) : Result<Nothing>()

    val isSuccess get() = this is Success

    val isFailure get() = !isSuccess

    companion object {
        fun <T> success(value: T): Result<T> = Success(value)

        fun <T> failure(exception: Throwable): Result<T> = Failure(exception)

        inline fun <T> runCatching(block: () -> T) = try {
            success(block())
        } catch (t: Throwable) {
            failure(t)
        }
    }
}

inline fun <R, T> Result<T>.map(transform: (value: T) -> R) = when (this) {
    is Result.Success -> Result.success(transform(value))
    is Result.Failure -> Result.failure(exception)
}

inline fun <R, T> Result<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (exception: Throwable) -> R,
) = when (this) {
    is Result.Success -> onSuccess(value)
    is Result.Failure -> onFailure(exception)
}
