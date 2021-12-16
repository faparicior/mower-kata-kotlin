package mower.mower.value_object

import mower.mower.exception.InvalidOrientationException

@JvmInline
value class MowerOrientation private constructor(val value: String) {
    private enum class Compass(val stepDirection: Int, val affectsXAxis: Boolean, val affectsYAxis: Boolean) {
        N(POSITIVE_DIRECTION, NOT_X_AXIS, Y_AXIS),
        E(POSITIVE_DIRECTION, X_AXIS, NOT_Y_AXIS),
        S(NEGATIVE_DIRECTION, NOT_X_AXIS, Y_AXIS),
        W(NEGATIVE_DIRECTION, X_AXIS, NOT_Y_AXIS)
    }

    init {
        try {
            Compass.valueOf(value)
        } catch (exception: IllegalArgumentException) {
            throw InvalidOrientationException.withValues(value, Compass.values().contentToString())
        }
    }

    companion object {
        private const val COMPASS_STEP: Int = 1
        private const val POSITIVE_DIRECTION: Int = 1
        private const val NEGATIVE_DIRECTION: Int = -1
        private const val NOT_X_AXIS: Boolean = false
        private const val NOT_Y_AXIS: Boolean = false
        private const val X_AXIS: Boolean = true
        private const val Y_AXIS: Boolean = true

        fun build(value: String): MowerOrientation {
            return MowerOrientation(value)
        }
    }

    fun changeOrientation(mowerMovement: MowerMovement): MowerOrientation {
        val currentCompass = Compass.valueOf(value)

        if (mowerMovement.isClockWise()) {
            val futureCompass = Compass.values().getOrElse(currentCompass.ordinal + COMPASS_STEP) { Compass.N }
            return MowerOrientation(futureCompass.name)
        }

        if (mowerMovement.isCounterClockWise()) {
            val futureCompass = Compass.values().getOrElse(currentCompass.ordinal - COMPASS_STEP) { Compass.W }
            return MowerOrientation(futureCompass.name)
        }

        return this
    }

    fun affectsYAxis(): Boolean {
        return Compass.valueOf(value).affectsYAxis
    }

    fun affectsXAxis(): Boolean {
        return Compass.valueOf(value).affectsXAxis
    }

    fun stepMovement(): Int {
        return Compass.valueOf(value).stepDirection
    }
}