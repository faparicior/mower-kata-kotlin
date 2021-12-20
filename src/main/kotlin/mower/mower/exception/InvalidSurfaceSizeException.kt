package mower.mower.exception

class InvalidSurfaceSizeException private constructor (message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    companion object {
        private const val MESSAGE: String = "Invalid surface size %d. Only positive values are valid."

        @JvmStatic
        fun withValue(value: Int): InvalidSurfaceSizeException
        {
            return InvalidSurfaceSizeException(String.format(MESSAGE, value), null)
        }
    }
}