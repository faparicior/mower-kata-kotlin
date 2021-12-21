package mower.mower.domain.value_object

import mower.mower.domain.exception.InvalidOrientationException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.test.Test

private const val ORIENTATION: String = "N"
private const val INVALID_ORIENTATION: String = "H"

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

        orientation = orientation.changeOrientation(movement)

        assertThat(orientation.value).isEqualTo(expectedOrientation.value)
    }

    @ParameterizedTest
    @MethodSource("orientationAndYAxisAffectationProvider")
    fun `Should eval if affects Y axis`(orientationData: String, affectsYAxis: Boolean) {
        val orientation = MowerOrientation.build(orientationData)

        assertThat(orientation.affectsYAxis()).isEqualTo(affectsYAxis)
    }

    @ParameterizedTest
    @MethodSource("orientationAndXAxisAffectationProvider")
    fun `Should eval if affects X axis`(orientationData: String, affectsXAxis: Boolean) {
        val orientation = MowerOrientation.build(orientationData)

        assertThat(orientation.affectsXAxis()).isEqualTo(affectsXAxis)
    }

    @ParameterizedTest
    @MethodSource("orientationAndStepDirectionProvider")
    fun `Should eval direction step` (orientationData: String, expectedStepDirection: Int) {
        val orientation = MowerOrientation.build(orientationData)

        assertThat(orientation.stepMovement()).isEqualTo(expectedStepDirection)
    }

    companion object {
        @JvmStatic
        fun orientationAndMovementProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("N", "F", "N"),
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

        @JvmStatic
        fun orientationAndYAxisAffectationProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("N", true),
                Arguments.arguments("S", true),
                Arguments.arguments("E", false),
                Arguments.arguments("W", false)
            )
        }

        @JvmStatic
        fun orientationAndXAxisAffectationProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("N", false),
                Arguments.arguments("S", false),
                Arguments.arguments("E", true),
                Arguments.arguments("W", true)
            )
        }

        @JvmStatic
        fun orientationAndStepDirectionProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("N", 1),
                Arguments.arguments("E", 1),
                Arguments.arguments("S", -1),
                Arguments.arguments("W", -1),
            )
        }
    }
}
