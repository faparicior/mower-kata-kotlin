package mower.mower.value_object

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import kotlin.test.Test

private const val X_MOWER_POSITION: Int = 5
private const val Y_MOWER_POSITION: Int = 5
private const val ORIENTATION: String = "N"

internal class MowerPositionTest
{
    @Test
    fun `Should be build`() {
        val mowerPosition = MowerPosition.build(
            XMowerPosition.build(X_MOWER_POSITION),
            YMowerPosition.build(Y_MOWER_POSITION),
            MowerOrientation.build(ORIENTATION)
        )

        assertThat(mowerPosition.xPosition.value).isEqualTo(X_MOWER_POSITION)
        assertThat(mowerPosition.yPosition.value).isEqualTo(Y_MOWER_POSITION)
        assertThat(mowerPosition.orientation.value).isEqualTo(ORIENTATION)
    }

    @Test
    fun `Should change orientation with left movement`() {
        var position = MowerPosition.build(
            XMowerPosition.build(X_MOWER_POSITION),
            YMowerPosition.build(Y_MOWER_POSITION),
            MowerOrientation.build("N")
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
            MowerOrientation.build("N")
        )

        val mowerMovement = MowerMovement.build("R")
        val expectedOrientation = MowerOrientation.build("E")

        position = position.move(mowerMovement)

        assertThat(position.orientation).isEqualTo(expectedOrientation)
    }

    @Test
    @Disabled("To implement")
    fun `Should be able to change position`(){
        var position = MowerPosition.build(
            XMowerPosition.build(X_MOWER_POSITION),
            YMowerPosition.build(Y_MOWER_POSITION),
            MowerOrientation.build("N")
        )

        val mowerMovement = MowerMovement.build("F")
        val expectedPosition = MowerPosition.build(
            XMowerPosition.build(X_MOWER_POSITION),
            YMowerPosition.build(Y_MOWER_POSITION + 1),
            MowerOrientation.build("N")
        )
        position = position.move(mowerMovement)

        assertThat(position.xPosition.value).isEqualTo(expectedPosition.xPosition.value)
        assertThat(position.yPosition.value).isEqualTo(expectedPosition.yPosition.value)
    }
}