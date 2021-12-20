package mower.mower.exception

class InvalidMowerPositionException private constructor (message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    companion object {
        private const val MESSAGE: String = "Invalid Y position %d. Only positive values are valid."

        @JvmStatic
        fun withValue(value: Int): InvalidMowerPositionException
        {
            return InvalidMowerPositionException(String.format(MESSAGE, value), null)
        }
    }
}