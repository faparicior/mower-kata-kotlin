package mower.mower.value_object

import mower.mower.exception.InvalidSurfaceSizeException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

private const val X_SIZE: Int = 5
private const val INVALID_X_SIZE: Int = -1

internal class SurfaceXSizeTest {
    @Test
    fun `Should be build` ()
    {
        val surfaceXSize = SurfaceXSize.build(X_SIZE)

        assertInstanceOf(SurfaceXSize::class.java, surfaceXSize)
        Assertions.assertThat(surfaceXSize.value).isEqualTo(X_SIZE)
    }

    @Test
    fun `Should throw exception for negative values` ()
    {
        assertThrows(InvalidSurfaceSizeException::class.java) {
            SurfaceXSize.build(INVALID_X_SIZE)
        }
    }
}