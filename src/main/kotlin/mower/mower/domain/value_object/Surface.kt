package mower.mower.domain.value_object

class Surface private constructor(val xSize: SurfaceXSize, val ySize: SurfaceYSize) {
    companion object {
        @JvmStatic
        fun build(xSize: SurfaceXSize, ySize: SurfaceYSize): Surface {
            return Surface(xSize, ySize)
        }
    }
}
