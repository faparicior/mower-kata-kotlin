package mower.mower.value_object

import mower.mower.exception.InvalidMowerPositionException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

private const val Y_POSITION: Int = 3
private const val INVALID_POSITION: Int = -1
private const val STEP: Int = 1

internal class YMowerPositionTest
{
    @Test
    fun `Should be build` ()
    {
        val yMowerPosition = YMowerPosition.build(Y_POSITION)

        assertInstanceOf(YMowerPosition::class.java, yMowerPosition)
        assertThat(yMowerPosition.value).isEqualTo(Y_POSITION)
    }

    @Test
    fun `Should throw exception for negative values` ()
    {
        assertThrows(InvalidMowerPositionException::class.java) {
            YMowerPosition.build(INVALID_POSITION)
        }
    }

    @Test
    fun `Should sum a Step forward`() {
        val yMowerPosition = YMowerPosition.build(Y_POSITION)

        assertThat(yMowerPosition.moveForward(STEP).value).isEqualTo(Y_POSITION + STEP)
    }
}