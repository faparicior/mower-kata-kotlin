package mower.mower.value_object

class MowerPosition private constructor(val xPosition: XMowerPosition, val yPosition: YMowerPosition, val orientation: MowerOrientation) {

    fun move(mowerMovement: MowerMovement): MowerPosition {
        if (mowerMovement.isForward()) {
            // return move front
        }

        return MowerPosition(xPosition, yPosition, orientation.applyMovement(mowerMovement))
    }

    companion object {
        fun build(xPosition: XMowerPosition, yPosition: YMowerPosition, orientation: MowerOrientation): MowerPosition {
            return MowerPosition(xPosition, yPosition, orientation)
        }
    }
}
