package mower.mower.value_object

import mower.mower.exception.InvalidOrientationException

@JvmInline
value class Orientation private constructor(val value: String) {
    private enum class Orientations(val value: String){
        NORTH("N"),
        EAST("E"),
        SOUTH("S"),
        WEST("W")
    }

    init {
        if(null === enumValues<Orientations>().find { it.value == value}){
            throw InvalidOrientationException.withValues(value, Orientations.values().map { it.value }.toString())
        }
    }

    companion object {
        fun build(value: String): Orientation
        {
            return Orientation(value)
        }
    }
}