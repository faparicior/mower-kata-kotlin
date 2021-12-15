package mower.mower.value_object

import mower.mower.exception.InvalidOrientationException

@JvmInline
value class MowerOrientation private constructor(val value: String) {
    private enum class Compass{
        N, E, S, W
    }

    init {
        try {
            Compass.valueOf(value)
        } catch (exception: IllegalArgumentException) {
            throw InvalidOrientationException.withValues(value, Compass.values().contentToString())
        }
    }

    companion object {
        private const val COMPASS_STEP = 1

        fun build(value: String): MowerOrientation
        {
            return MowerOrientation(value)
        }
    }

    fun applyMovement(mowerMovement: MowerMovement): MowerOrientation {
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
}