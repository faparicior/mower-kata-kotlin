package mower.mower

import mower.mower.value_object.MowerMovement
import mower.mower.value_object.MowerPosition

class Mower private constructor(private var mowerPosition: MowerPosition) {

    fun move(movement: MowerMovement) {
        mowerPosition = mowerPosition.move(movement)
    }

    companion object {
        fun build(mowerPosition: MowerPosition): Mower {
            return Mower(mowerPosition)
        }
    }

    fun mowerPosition(): MowerPosition {
        return mowerPosition
    }
}
