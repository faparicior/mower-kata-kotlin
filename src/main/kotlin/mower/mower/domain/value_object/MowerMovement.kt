package mower.mower.domain.value_object

import mower.mower.domain.exception.InvalidMovementException

class MowerMovement private constructor(val value: String){
    private enum class Movements { L, R, F }

    init {
        try {
            Movements.valueOf(value)
        } catch (exception: IllegalArgumentException) {
            throw InvalidMovementException.withValues(value, Movements.values().contentToString())
        }
    }

    companion object {
        @JvmStatic
        fun build(value: String): MowerMovement {
            return MowerMovement(value)
        }
    }

    fun isForward(): Boolean {
        return value == Movements.F.name
    }

    fun isClockWise(): Boolean {
        return value == Movements.R.name
    }

    fun isCounterClockWise(): Boolean {
        return value == Movements.L.name
    }
}