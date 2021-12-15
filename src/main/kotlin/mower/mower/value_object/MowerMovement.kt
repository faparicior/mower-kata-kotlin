package mower.mower.value_object

import mower.mower.exception.InvalidMovementException

class MowerMovement private constructor(val value: String){
    private enum class Movements (val value: String){
        LEFT("L"),
        RIGHT("R"),
        FORWARD("F")
    }

    init {
        if(null === enumValues<Movements>().find { it.value == value}){
            throw InvalidMovementException.withValues(value, Movements.values().map { it.value }.toString())
        }
    }

    companion object {
        fun build(value: String): MowerMovement {
            return MowerMovement(value)
        }
    }

    fun isForward(): Boolean {
        return value == Movements.FORWARD.value
    }

    fun isClockWise(): Boolean {
        return value == Movements.RIGHT.value
    }

    fun isCounterClockWise(): Boolean {
        return value == Movements.LEFT.value
    }
}