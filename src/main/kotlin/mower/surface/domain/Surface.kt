package mower.surface.domain

import mower.surface.domain.value_object.SurfaceXSize
import mower.surface.domain.value_object.SurfaceYSize

class Surface private constructor(val xSize: SurfaceXSize, val ySize: SurfaceYSize) {
    companion object {
        @JvmStatic
        fun build(xSize: SurfaceXSize, ySize: SurfaceYSize): Surface {
            return Surface(xSize, ySize)
        }
    }
}
