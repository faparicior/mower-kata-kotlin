package mower.mower.value_object

import mower.mower.exception.InvalidMowerPositionException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

private const val X_POSITION: Int = 3
private const val INVALID_POSITION: Int = -1
private const val STEP: Int = 1

internal class XMowerPositionTest
{
    @Test
    fun `Should be build` ()
    {
        val xMowerPosition = XMowerPosition.build(X_POSITION)

        assertInstanceOf(XMowerPosition::class.java, xMowerPosition)
        assertThat(xMowerPosition.value).isEqualTo(X_POSITION)
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
        val xMowerPosition = XMowerPosition.build(X_POSITION)

        assertThat(xMowerPosition.moveForward(STEP).value).isEqualTo(X_POSITION + STEP)
    }
}