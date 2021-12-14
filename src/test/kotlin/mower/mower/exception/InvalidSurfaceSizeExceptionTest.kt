package mower.mower.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

private const val POSITION = -4
private const val MESSAGE = "Invalid surface size $POSITION. Only positive values are valid."

internal class InvalidSurfaceSizeExceptionTest
{
    @Test
    fun `Should be build with custom value`() {
        val exception = assertThrows(InvalidSurfaceSizeException::class.java) {
            throw InvalidSurfaceSizeException.withValue(POSITION)
        }

        assertThat(exception.message).isEqualTo(MESSAGE)
    }
}