package mower.mower

import mower.mower.value_object.SurfaceXSize
import mower.mower.value_object.SurfaceYSize

class Surface private constructor(val xSize: SurfaceXSize, val ySize: SurfaceYSize) {
    companion object {
        fun build(xSize: SurfaceXSize, ySize: SurfaceYSize): Surface {
            return Surface(xSize, ySize)
        }
    }
}
