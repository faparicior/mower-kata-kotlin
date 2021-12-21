package mower.mower.domain.value_object

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.test.Test

private const val MOVE_FORWARD: String = "F"
private const val INVALID_MOVEMENT: String = "J"

internal class MowerMovementTest
{
    @Test
    fun `Should be build`() {
        val movement = MowerMovement.build(MOVE_FORWARD)

        assertInstanceOf(MowerMovement::class.java, movement)
    }

    @ParameterizedTest
    @ValueSource(strings = [ "L", "R", "F"])
    fun `Should accept valid values`(value: String) {
        val movement = MowerMovement.build(value)

        assertThat(movement.value).isEqualTo(value)
    }

    @Test
    fun `Should throw exception for invalid movement`() {
        assertThrows(Exception::class.java) {
            MowerMovement.build(INVALID_MOVEMENT)
        }
    }

    @ParameterizedTest
    @MethodSource("movementForwardEvaluation")
    fun `Should eval if movement is forward`(movementData: String, expectedResult: Boolean) {
        val movement = MowerMovement.build(movementData)

        assertThat(movement.isForward()).isEqualTo(expectedResult)
    }

    @ParameterizedTest
    @MethodSource("movementClockWiseEvaluation")
    fun `Should eval if movement is clockwise`(movementData: String, expectedResult: Boolean) {
        val movement = MowerMovement.build(movementData)

        assertThat(movement.isClockWise()).isEqualTo(expectedResult)
    }

    @ParameterizedTest
    @MethodSource("movementCounterClockWiseEvaluation")
    fun `Should eval if movement is counterclockwise`(movementData: String, expectedResult: Boolean) {
        val movement = MowerMovement.build(movementData)

        assertThat(movement.isCounterClockWise()).isEqualTo(expectedResult)
    }

    companion object {
        @JvmStatic
        fun movementForwardEvaluation(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("F", true),
                Arguments.arguments("L", false),
                Arguments.arguments("R", false)
            )
        }

        @JvmStatic
        fun movementClockWiseEvaluation(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("F", false),
                Arguments.arguments("L", false),
                Arguments.arguments("R", true)
            )
        }

        @JvmStatic
        fun movementCounterClockWiseEvaluation(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("F", false),
                Arguments.arguments("L", true),
                Arguments.arguments("R", false)
            )
        }
    }
}