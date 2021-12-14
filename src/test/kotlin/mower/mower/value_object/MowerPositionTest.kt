package mower.mower.value_object

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

private const val X_MOWER_POSITION: Int = 5
private const val Y_MOWER_POSITION: Int = 5
private const val ORIENTATION: String = "N"

internal class MowerPositionTest
{
    @Test
    fun `Should be build`()
    {
        val mowerPosition = MowerPosition.build(
            XMowerPosition.build(X_MOWER_POSITION),
            YMowerPosition.build(Y_MOWER_POSITION),
            MowerOrientation.build(ORIENTATION)
        )

        assertThat(mowerPosition.xPosition.value).isEqualTo(X_MOWER_POSITION)
        assertThat(mowerPosition.yPosition.value).isEqualTo(Y_MOWER_POSITION)
        assertThat(mowerPosition.orientation).isEqualTo(ORIENTATION)
    }
}