package mower.mower.domain

import mower.mower.domain.exception.InvalidMowerPositionException
import mower.mower.domain.value_object.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream
import kotlin.test.Test
import kotlin.test.assertContains

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
    private lateinit var surface: Surface

    @BeforeEach
    fun setUp() {
        mowerPosition = MowerPosition.build(
            XMowerPosition.build(X_POSITION),
            YMowerPosition.build(Y_POSITION),
            MowerOrientation.build(NORTH_ORIENTATION)
        )

        mower = Mower.build(mowerId, mowerPosition)

        surface = Surface.build(
            SurfaceXSize.build(5),
            SurfaceYSize.build(5)
        )
    }

    @Test
    fun `Should be build`() {
        val mower = Mower.build(mowerId, mowerPosition)

        assertThat(mower).isInstanceOf(Mower::class.java)
        assertThat(mower.mowerId).isEqualTo(mowerId)
        assertThat(mower.mowerPosition()).isEqualTo(mowerPosition)
    }

    @Test
    fun `Should be able to turn left`() {
        val expectedMowerOrientation = MowerOrientation.build(WEST_ORIENTATION)

        mower.move(MowerMovement.build(MOVE_LEFT), surface)
        assertThat(mower.mowerPosition().orientation).isEqualTo(expectedMowerOrientation)
    }

    @Test
    fun `Should be able to turn right`() {
        val expectedMowerOrientation = MowerOrientation.build(EAST_ORIENTATION)

        mower.move(MowerMovement.build(MOVE_RIGHT), surface)
        assertThat(mower.mowerPosition().orientation).isEqualTo(expectedMowerOrientation)
    }

    @Test
    fun `Should be able to move forward`() {
        mower.move(MowerMovement.build(MOVE_FORWARD), surface)
        assertThat(mower.mowerPosition().xPosition.value).isEqualTo(X_POSITION)
        assertThat(mower.mowerPosition().yPosition.value).isEqualTo(Y_POSITION + STEP)
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("positionAndMovementProvider")
    fun `Should throw exception if goes out of bounds`(
        scenario: String,
        xPosition: Int,
        yPosition: Int,
        orientation: String,
        message: String
    ) {
        val mowerPosition = MowerPosition.build(
            XMowerPosition.build(xPosition),
            YMowerPosition.build(yPosition),
            MowerOrientation.build(orientation)
        )

        val mower = Mower.build(mowerId, mowerPosition)

        val exception = assertThrows(InvalidMowerPositionException::class.java) {
            mower.move(MowerMovement.build(MOVE_FORWARD), surface)
        }

        assertContains(exception.message.toString(), message)
    }

    companion object {
        @JvmStatic
        fun positionAndMovementProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("Out of bounds by negative value movement axis Y", 5, 0, SOUTH_ORIENTATION, "Only positive values are valid"),
                Arguments.arguments("Out of bounds by negative value movement axis X", 0, 5, WEST_ORIENTATION, "Only positive values are valid"),
                Arguments.arguments("Out of bounds movement axis Y", 0, 5, NORTH_ORIENTATION, "Out of bounds"),
                Arguments.arguments("Out of bounds movement axis X", 5, 0, EAST_ORIENTATION, "Out of bounds")
            )
        }
    }
}