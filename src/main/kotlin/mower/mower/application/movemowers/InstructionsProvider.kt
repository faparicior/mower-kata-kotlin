package mower.mower.application.movemowers

import mower.mower.domain.value_object.MowerPosition

interface InstructionsProvider {
    fun surface(): String
    fun totalMowers(): Int
    fun mowerPosition(index: Int): MowerPosition
    fun mowerInstructions(index: Int): String
    fun load(fileName: String)
}