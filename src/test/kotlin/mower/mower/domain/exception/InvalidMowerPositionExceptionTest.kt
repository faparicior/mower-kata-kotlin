package mower.mower.domain.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

private const val NEGATIVE_POSITION: Int = -4
private const val X_POSITION: Int = 2
private const val Y_POSITION: Int = 3
private const val MESSAGE: String = "Invalid position $NEGATIVE_POSITION. Only positive values are valid."
private const val MESSAGE_OUT_OF_BOUNDS: String = "Invalid position X:$X_POSITION Y:$Y_POSITION. Out of bounds."

internal class InvalidMowerPositionExceptionTest
{
    @Test
    fun `Should be build with custom value`() {
        val exception = assertThrows(InvalidMowerPositionException::class.java) {
            throw InvalidMowerPositionException.withValue(NEGATIVE_POSITION)
        }

        assertThat(exception.message).isEqualTo(MESSAGE)
    }

    @Test
    fun `Should be build with out of bounds context`() {
        val exception = assertThrows(InvalidMowerPositionException::class.java) {
            throw InvalidMowerPositionException.outOfBounds(X_POSITION, Y_POSITION)
        }

        assertThat(exception.message).isEqualTo(MESSAGE_OUT_OF_BOUNDS)
    }
}