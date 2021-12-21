package mower.mower.domain.value_object

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.Test

private const val X_MOWER_POSITION: Int = 5
private const val Y_MOWER_POSITION: Int = 5
private const val NORTH_ORIENTATION: String = "N"

internal class MowerPositionTest
{
    @Test
    fun `Should be build`() {
        val mowerPosition = MowerPosition.build(
            XMowerPosition.build(X_MOWER_POSITION),
            YMowerPosition.build(Y_MOWER_POSITION),
            MowerOrientation.build(NORTH_ORIENTATION)
        )

        assertThat(mowerPosition.xPosition.value).isEqualTo(X_MOWER_POSITION)
        assertThat(mowerPosition.yPosition.value).isEqualTo(Y_MOWER_POSITION)
        assertThat(mowerPosition.orientation.value).isEqualTo(NORTH_ORIENTATION)
    }

    @Test
    fun `Should change orientation with left movement`() {
        var position = MowerPosition.build(
            XMowerPosition.build(X_MOWER_POSITION),
            YMowerPosition.build(Y_MOWER_POSITION),
            MowerOrientation.build(NORTH_ORIENTATION)
        )

        val mowerMovement = MowerMovement.build("L")
        val expectedOrientation = MowerOrientation.build("W")

        position = position.move(mowerMovement)

        assertThat(position.orientation).isEqualTo(expectedOrientation)
    }

    @Test
    fun `Should change orientation with right movement`() {
        var position = MowerPosition.build(
            XMowerPosition.build(X_MOWER_POSITION),
            YMowerPosition.build(Y_MOWER_POSITION),
            MowerOrientation.build(NORTH_ORIENTATION)
        )

        val mowerMovement = MowerMovement.build("R")
        val expectedOrientation = MowerOrientation.build("E")

        position = position.move(mowerMovement)

        assertThat(position.orientation).isEqualTo(expectedOrientation)
    }

    @ParameterizedTest
    @MethodSource("orientationAndPositionMovement")
    fun `Should be able to change position`(orientation: String, expectedXPosition: Int, expectedYPosition: Int) {
        var position = MowerPosition.build(
            XMowerPosition.build(X_MOWER_POSITION),
            YMowerPosition.build(Y_MOWER_POSITION),
            MowerOrientation.build(orientation)
        )

        val mowerMovement = MowerMovement.build("F")
        val expectedPosition = MowerPosition.build(
            XMowerPosition.build(expectedXPosition),
            YMowerPosition.build(expectedYPosition),
            MowerOrientation.build("N")
        )
        position = position.move(mowerMovement)

        assertThat(position.xPosition.value).isEqualTo(expectedPosition.xPosition.value)
        assertThat(position.yPosition.value).isEqualTo(expectedPosition.yPosition.value)
    }

    companion object {
        @JvmStatic
        fun orientationAndPositionMovement(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("N", 5, 6),
                Arguments.arguments("E", 6, 5),
                Arguments.arguments("S", 5, 4),
                Arguments.arguments("W", 4, 5)
            )
        }
    }
}