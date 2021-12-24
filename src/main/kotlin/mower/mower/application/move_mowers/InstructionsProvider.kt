package mower.mower.application.move_mowers

import mower.mower.domain.value_object.MowerPosition

interface InstructionsProvider {
    fun surface(): String
    fun totalMowers(): Int
    fun mowerPosition(index: Int): MowerPosition
    fun mowerInstructions(index: Int): String
    fun load(instructions: Array<String>)
}