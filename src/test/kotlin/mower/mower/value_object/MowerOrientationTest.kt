package mower.mower.value_object

import mower.mower.exception.InvalidOrientationException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
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

    @ParameterizedTest(name = "{index} => orientation = ''{0}''")
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

    @ParameterizedTest(name = "{index} => orientation = ''{0}'', movement = ''{1}'', result = ''{2}''")
    @MethodSource("orientationAndMovementProvider")
    fun `Should apply orientation movements`(orientationData: String, movementData: String, expectedOrientationData: String) {
        var orientation = MowerOrientation.build(orientationData)
        val movement = MowerMovement.build(movementData)
        val expectedOrientation = MowerOrientation.build(expectedOrientationData)

        orientation = orientation.applyMovement(movement)

        assertThat(orientation.value).isEqualTo(expectedOrientation.value)
    }

    companion object {
        @JvmStatic
        fun orientationAndMovementProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("N", "R", "E"),
                Arguments.arguments("E", "R", "S"),
                Arguments.arguments("S", "R", "W"),
                Arguments.arguments("W", "R", "N"),
                Arguments.arguments("N", "L", "W"),
                Arguments.arguments("W", "L", "S"),
                Arguments.arguments("S", "L", "E"),
                Arguments.arguments("E", "L", "N")
            )
        }
    }
}
