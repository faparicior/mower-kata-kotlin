package mower.mower.value_object

import mower.mower.exception.InvalidSurfaceSizeException

@JvmInline
value class SurfaceYSize private constructor(val value: Int) {
    init {
        if(value < MINIMUM_SIZE_VALUE){
            throw InvalidSurfaceSizeException.withValue(value)
        }
    }

    companion object {
        private const val MINIMUM_SIZE_VALUE: Int = 0

        @JvmStatic
        fun build(value: Int): SurfaceYSize
        {
            return SurfaceYSize(value)
        }
    }
}