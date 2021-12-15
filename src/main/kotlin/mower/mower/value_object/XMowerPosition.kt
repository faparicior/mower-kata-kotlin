package mower.mower.value_object

import mower.mower.exception.InvalidMowerPositionException

@JvmInline
value class XMowerPosition private constructor (val value: Int) {
    init {
        if(value < MINIMUM_AXIS_VALUE){
            throw InvalidMowerPositionException.withValue(value)
        }
    }

    companion object {
        private const val MINIMUM_AXIS_VALUE: Int = 0

        fun build(value: Int): XMowerPosition {
            return XMowerPosition(value)
        }
    }

    fun moveForward(step: Int): XMowerPosition {
        return XMowerPosition(value + step)
    }
}