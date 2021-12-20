package mower.mower

import mower.mower.exception.InvalidMowerPositionException
import mower.mower.value_object.MowerId
import mower.mower.value_object.MowerMovement
import mower.mower.value_object.MowerPosition

class Mower private constructor(val mowerId: MowerId, private var mowerPosition: MowerPosition) {

    fun move(movement: MowerMovement, surface: Surface) {
        mowerPosition = mowerPosition.move(movement)

        if (mowerIsOutOfBounds(surface)) {
            throw InvalidMowerPositionException.outOfBounds(mowerPosition.xPosition.value, mowerPosition.yPosition.value)
        }
    }

    private fun mowerIsOutOfBounds(surface: Surface) =
        mowerPosition.xPosition.value > surface.xSize.value || mowerPosition.yPosition.value > surface.ySize.value

    companion object {
        @JvmStatic
        fun build(mowerId: MowerId, mowerPosition: MowerPosition): Mower {
            return Mower(mowerId, mowerPosition)
        }
    }

    fun mowerPosition(): MowerPosition {
        return mowerPosition
    }
}
