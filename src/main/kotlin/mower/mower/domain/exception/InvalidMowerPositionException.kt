package mower.mower.domain.exception

class InvalidMowerPositionException private constructor (message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    companion object {
        private const val MESSAGE: String = "Invalid position %d. Only positive values are valid."
        private const val MESSAGE_OUT_OF_BOUNDS: String = "Invalid position X:%d Y:%d. Out of bounds."

        @JvmStatic
        fun withValue(value: Int): InvalidMowerPositionException
        {
            return InvalidMowerPositionException(String.format(MESSAGE, value), null)
        }

        @JvmStatic
        fun outOfBounds(xValue: Int, yValue: Int): InvalidMowerPositionException
        {
            return InvalidMowerPositionException(String.format(MESSAGE_OUT_OF_BOUNDS, xValue, yValue), null)
        }
    }
}