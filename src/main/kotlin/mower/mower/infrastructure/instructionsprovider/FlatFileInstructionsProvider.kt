package mower.mower.infrastructure.instructionsprovider

import mower.mower.application.movemowers.InstructionsProvider
import mower.mower.domain.value_object.*
import java.io.File

private const val REGISTRIES_BY_MOWER = 2

private const val X_POSITION_INDEX = 0
private const val Y_POSITION_INDEX = 1
private const val ORIENTATION_INDEX = 2

class FlatFileInstructionsProvider: InstructionsProvider {

    private lateinit var instructions: MutableList<String>
    private lateinit var surface: String

    override fun load(fileName: String){
        this.instructions = File(fileName).readLines().toMutableList()
        surface = this.instructions.removeFirst()
    }

    override fun surface(): String {
        return surface
    }

    override fun totalMowers(): Int {
        return instructions.size / REGISTRIES_BY_MOWER
    }

    override fun mowerPosition(index: Int): MowerPosition {
        val mowerPositionIndex: Int = realIndex(index)
        val mowerPositionData = instructions[mowerPositionIndex]
        val xMowerPosition = XMowerPosition.build(mowerPositionData.split(" ")[X_POSITION_INDEX].toInt())
        val yMowerPosition = YMowerPosition.build(mowerPositionData.split(" ")[Y_POSITION_INDEX].toInt())
        val mowerOrientation = MowerOrientation.build(mowerPositionData.split(" ")[ORIENTATION_INDEX])

        return MowerPosition.build(xMowerPosition, yMowerPosition, mowerOrientation)
    }

    override fun mowerInstructions(index: Int): String {
        val mowerInstructionsIndex: Int = realIndex(index)
        return instructions[mowerInstructionsIndex + 1]
    }

    private fun realIndex(index: Int) = if (0 == index) 0 else index + 1
}