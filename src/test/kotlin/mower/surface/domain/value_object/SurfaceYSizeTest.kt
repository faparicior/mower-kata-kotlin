package mower.surface.domain.value_object

import mower.surface.domain.exception.InvalidSurfaceSizeException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

private const val Y_SIZE: Int = 5
private const val INVALID_Y_SIZE: Int = -1

internal class SurfaceYSizeTest
{
    @Test
    fun `Should be build` ()
    {
        val surfaceYSize = SurfaceYSize.build(Y_SIZE)

        assertInstanceOf(SurfaceYSize::class.java, surfaceYSize)
        Assertions.assertThat(surfaceYSize.value).isEqualTo(Y_SIZE)
    }

    @Test
    fun `Should throw exception for negative values` ()
    {
        assertThrows(InvalidSurfaceSizeException::class.java) {
            SurfaceYSize.build(INVALID_Y_SIZE)
        }
    }
}