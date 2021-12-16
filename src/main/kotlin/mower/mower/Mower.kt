package mower.mower

import mower.mower.value_object.MowerId
import mower.mower.value_object.MowerMovement
import mower.mower.value_object.MowerPosition

class Mower private constructor(val mowerId: MowerId, private var mowerPosition: MowerPosition) {

    fun move(movement: MowerMovement) {
        mowerPosition = mowerPosition.move(movement)
    }

    companion object {
        fun build(mowerId: MowerId, mowerPosition: MowerPosition): Mower {
            return Mower(mowerId, mowerPosition)
        }
    }

    fun mowerPosition(): MowerPosition {
        return mowerPosition
    }
}
