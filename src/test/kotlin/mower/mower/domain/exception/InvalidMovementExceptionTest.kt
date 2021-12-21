package mower.mower.domain.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

private const val MOVEMENT: String = "J"
private const val VALID_VALUES: String = "L, R, F"
private const val MESSAGE: String = "Invalid movement $MOVEMENT. Only $VALID_VALUES values are valid."

internal class InvalidMovementExceptionTest
{
    @Test
    fun `Should be build with custom value`() {
        val exception = assertThrows(InvalidMovementException::class.java) {
            throw InvalidMovementException.withValues(MOVEMENT, VALID_VALUES)
        }

        assertThat(exception.message).isEqualTo(MESSAGE)
    }
}