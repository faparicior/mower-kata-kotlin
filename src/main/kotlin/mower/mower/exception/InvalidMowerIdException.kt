package mower.mower.exception

class InvalidMowerIdException private constructor (message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    companion object {
        private const val MESSAGE: String = "Invalid mower id %s. Is not a valid UUID."

        @JvmStatic
        fun withValue(value: String): InvalidMowerIdException
        {
            return InvalidMowerIdException(String.format(MESSAGE, value), null)
        }
    }
}