package mower.mower.domain.value_object

class MowerPosition private constructor(val xPosition: XMowerPosition, val yPosition: YMowerPosition, val orientation: MowerOrientation) {

    fun move(mowerMovement: MowerMovement): MowerPosition {
        if (mowerMovement.isForward()) {
            if (orientation.affectsYAxis()) {
                return MowerPosition(xPosition, yPosition.moveForward(orientation.stepMovement()), orientation)
            }

            if (orientation.affectsXAxis()) {
                return MowerPosition(xPosition.moveForward(orientation.stepMovement()), yPosition, orientation)
            }
        }

        return MowerPosition(xPosition, yPosition, orientation.changeOrientation(mowerMovement))
    }

    companion object {
        @JvmStatic
        fun build(xPosition: XMowerPosition, yPosition: YMowerPosition, orientation: MowerOrientation): MowerPosition {
            return MowerPosition(xPosition, yPosition, orientation)
        }
    }

    fun positionAsString(): String {
        return "${xPosition.value} ${yPosition.value} ${orientation.value}"
    }
}
