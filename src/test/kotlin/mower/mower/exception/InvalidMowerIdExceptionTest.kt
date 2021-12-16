package mower.mower.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

private const val INVALID_UUID: String = "INVALID_UUID"
private const val MESSAGE: String = "Invalid mower id $INVALID_UUID. Is not a valid UUID."

internal class InvalidMowerIdExceptionTest
{
    @Test
    fun `Should be build with custom value`() {
        val exception = assertThrows(InvalidMowerIdException::class.java) {
            throw InvalidMowerIdException.withValue(INVALID_UUID)
        }

        assertThat(exception.message).isEqualTo(MESSAGE)
    }
}