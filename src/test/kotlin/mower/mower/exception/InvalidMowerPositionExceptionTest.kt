package mower.mower.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

private const val POSITION: Int = -4
private const val MESSAGE: String = "Invalid Y position $POSITION. Only positive values are valid."

internal class InvalidMowerPositionExceptionTest
{
    @Test
    fun `Should be build with custom value`() {
        val exception = assertThrows(InvalidMowerPositionException::class.java) {
            throw InvalidMowerPositionException.withValue(POSITION)
        }

        assertThat(exception.message).isEqualTo(MESSAGE)
    }
}