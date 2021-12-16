package mower.mower

import mower.mower.value_object.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val MOVE_LEFT: String = "L"
private const val MOVE_RIGHT: String = "R"
private const val MOVE_FORWARD: String = "F"

private const val X_POSITION: Int = 5
private const val Y_POSITION: Int = 5
private const val STEP: Int = 1

private const val NORTH_ORIENTATION: String = "N"
private const val WEST_ORIENTATION: String = "W"
private const val EAST_ORIENTATION: String = "E"

internal class MowerTest {
    private lateinit var mower: Mower

    @BeforeEach
    fun setUp() {
        this.mower = Mower.build(MowerPosition.build(
            XMowerPosition.build(X_POSITION),
            YMowerPosition.build(Y_POSITION),
            MowerOrientation.build(NORTH_ORIENTATION)
        ))
    }

    @Test
    fun `Should be build`()
    {
        this.mower = Mower.build(
            MowerPosition.build(
                XMowerPosition.build(X_POSITION),
                YMowerPosition.build(Y_POSITION),
                MowerOrientation.build(NORTH_ORIENTATION)
            )
        )

        assertThat(this.mower).isInstanceOf(Mower::class.java)
    }

    @Test
    fun `Should be able to turn left`()
    {
        val expectedMowerOrientation = MowerOrientation.build(WEST_ORIENTATION)

        this.mower.move(MowerMovement.build(MOVE_LEFT))
        assertThat(mower.mowerPosition().orientation).isEqualTo(expectedMowerOrientation)
    }

    @Test
    fun `Should be able to turn right`()
    {
        val expectedMowerOrientation = MowerOrientation.build(EAST_ORIENTATION)

        this.mower.move(MowerMovement.build(MOVE_RIGHT))
        assertThat(mower.mowerPosition().orientation).isEqualTo(expectedMowerOrientation)
    }

    @Test
    fun `Should be able to move forward`()
    {
        this.mower.move(MowerMovement.build(MOVE_FORWARD))
        assertThat(mower.mowerPosition().xPosition.value).isEqualTo(X_POSITION)
        assertThat(mower.mowerPosition().yPosition.value).isEqualTo(Y_POSITION + STEP)
    }
}