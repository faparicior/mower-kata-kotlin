package mower.mower.domain.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

private const val ORIENTATION: String = "H"
private const val VALID_VALUES: String = "N, S, E, W"
private const val MESSAGE: String = "Invalid orientation $ORIENTATION. Only $VALID_VALUES values are valid."

internal class InvalidOrientationExceptionTest
{
    @Test
    fun `Should be build with custom value`() {
        val exception = assertThrows(InvalidOrientationException::class.java) {
            throw InvalidOrientationException.withValues(ORIENTATION, VALID_VALUES)
        }

        assertThat(exception.message).isEqualTo(MESSAGE)
    }
}