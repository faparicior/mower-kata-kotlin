package mower.mower.domain

import mower.mower.domain.value_object.SurfaceXSize
import mower.mower.domain.value_object.SurfaceYSize

class Surface private constructor(val xSize: SurfaceXSize, val ySize: SurfaceYSize) {
    companion object {
        @JvmStatic
        fun build(xSize: SurfaceXSize, ySize: SurfaceYSize): Surface {
            return Surface(xSize, ySize)
        }
    }
}
