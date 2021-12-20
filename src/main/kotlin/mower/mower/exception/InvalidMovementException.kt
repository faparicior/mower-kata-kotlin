package mower.mower.exception

class InvalidMovementException private constructor (message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    companion object {
        private const val MESSAGE: String = "Invalid movement %s. Only %s values are valid."

        @JvmStatic
        fun withValues(value: String, validValues: String): InvalidMovementException
        {
            return InvalidMovementException(String.format(MESSAGE, value, validValues), null)
        }
    }
}