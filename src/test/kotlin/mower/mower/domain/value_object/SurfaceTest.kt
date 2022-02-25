package mower.mower.domain.value_object

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

internal class SurfaceTest
{
    @Test
    fun `Should be build with area size` ()
    {
        val surface = Surface.build(SurfaceXSize.build(5), SurfaceYSize.build(5))

        assertThat(surface).isInstanceOf(Surface::class.java)
        assertThat(surface.xSize.value).isEqualTo(5)
        assertThat(surface.ySize.value).isEqualTo(5)
    }
}