package mower.mower

import mower.mower.exception.InvalidMowerPositionException
import mower.mower.value_object.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.assertThrows
import java.util.*
import kotlin.test.Test

private const val MOVE_LEFT: String = "L"
private const val MOVE_RIGHT: String = "R"
private const val MOVE_FORWARD: String = "F"

private const val X_POSITION: Int = 1
private const val Y_POSITION: Int = 1
private const val STEP: Int = 1

private const val NORTH_ORIENTATION: String = "N"
private const val WEST_ORIENTATION: String = "W"
private const val EAST_ORIENTATION: String = "E"
private const val SOUTH_ORIENTATION: String = "S"

internal class MowerTest {
    private lateinit var mower: Mower
    private val mowerId: MowerId = MowerId.build(UUID.randomUUID().toString())
    private lateinit var mowerPosition: MowerPosition

    @BeforeEach
    fun setUp() {
        mowerPosition = MowerPosition.build(
            XMowerPosition.build(X_POSITION),
            YMowerPosition.build(Y_POSITION),
            MowerOrientation.build(NORTH_ORIENTATION)
        )

        mower = Mower.build(
            mowerId,
            mowerPosition
        )
    }

    @Test
    fun `Should be build`() {
        val mower = Mower.build(
            mowerId,
            mowerPosition
        )

        assertThat(mower).isInstanceOf(Mower::class.java)
        assertThat(mower.mowerId).isEqualTo(mowerId)
        assertThat(mower.mowerPosition()).isEqualTo(mowerPosition)
    }

    @Test
    fun `Should be able to turn left`() {
        val expectedMowerOrientation = MowerOrientation.build(WEST_ORIENTATION)

        mower.move(MowerMovement.build(MOVE_LEFT))
        assertThat(mower.mowerPosition().orientation).isEqualTo(expectedMowerOrientation)
    }

    @Test
    fun `Should be able to turn right`() {
        val expectedMowerOrientation = MowerOrientation.build(EAST_ORIENTATION)

        mower.move(MowerMovement.build(MOVE_RIGHT))
        assertThat(mower.mowerPosition().orientation).isEqualTo(expectedMowerOrientation)
    }

    @Test
    fun `Should be able to move forward`() {
        mower.move(MowerMovement.build(MOVE_FORWARD))
        assertThat(mower.mowerPosition().xPosition.value).isEqualTo(X_POSITION)
        assertThat(mower.mowerPosition().yPosition.value).isEqualTo(Y_POSITION + STEP)
    }

    @Test
    fun `Should throw exception if goes out of bounds`() {
        val mowerPosition = MowerPosition.build(
            XMowerPosition.build(X_POSITION),
            YMowerPosition.build(Y_POSITION),
            MowerOrientation.build(SOUTH_ORIENTATION)
        )

        val mower = Mower.build(
            mowerId,
            mowerPosition
        )

        mower.move(MowerMovement.build("F"))

        assertThrows(InvalidMowerPositionException::class.java) {
            mower.move(MowerMovement.build("F"))
        }
    }
}