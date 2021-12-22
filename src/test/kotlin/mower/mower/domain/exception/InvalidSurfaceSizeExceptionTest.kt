package mower.mower.domain.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

private const val POSITION:Int = -4
private const val MESSAGE: String = "Invalid surface size $POSITION. Only positive values are valid."

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