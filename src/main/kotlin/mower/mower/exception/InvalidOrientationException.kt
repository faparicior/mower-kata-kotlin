package mower.mower.exception

class InvalidOrientationException private constructor (message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    companion object {
        private const val MESSAGE: String = "Invalid orientation %s. Only %s values are valid."

        fun withValues(value: String, validValues: String): InvalidOrientationException
        {
            return InvalidOrientationException(String.format(MESSAGE, value, validValues), null)
        }
    }
}