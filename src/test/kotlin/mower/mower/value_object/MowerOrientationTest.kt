package mower.mower.value_object

import mower.mower.exception.InvalidOrientationException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.Test

private const val ORIENTATION = "N"
private const val INVALID_ORIENTATION = "H"

internal class MowerOrientationTest
{
    @Test
    fun `Should be build`() {
        val orientation = MowerOrientation.build(ORIENTATION)

        assertInstanceOf(MowerOrientation::class.java, orientation)
        assertThat(orientation.value).isEqualTo(ORIENTATION)
    }

    @ParameterizedTest
    @ValueSource(strings = [ "N", "S", "E", "W" ])
    fun `Should be build with valid orientations`(value: String) {
        val orientation = MowerOrientation.build(value)

        assertThat(orientation.value).isEqualTo(value)
    }

    @Test
    fun `Should throw exception for invalid Orientation`() {

        assertThrows(InvalidOrientationException::class.java) {
            MowerOrientation.build(INVALID_ORIENTATION)
        }
    }
}
