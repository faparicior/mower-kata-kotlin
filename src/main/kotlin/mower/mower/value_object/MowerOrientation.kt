package mower.mower.value_object

import mower.mower.exception.InvalidOrientationException

@JvmInline
value class MowerOrientation private constructor(val value: String) {
    private enum class Compass (val stepDirection: Int){
        N (POSITIVE_DIRECTION),
        E (POSITIVE_DIRECTION),
        S (NEGATIVE_DIRECTION),
        W (NEGATIVE_DIRECTION)
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

        fun build(value: String): MowerOrientation
        {
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
        return Compass.N.name == value || Compass.S.name == value
    }

    fun affectsXAxis(): Boolean {
        return Compass.E.name == value || Compass.W.name == value
    }

    fun stepMovement(): Int {
        return Compass.valueOf(value).stepDirection
    }
}