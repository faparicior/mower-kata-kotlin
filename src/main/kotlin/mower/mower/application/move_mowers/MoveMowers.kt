package mower.mower.application.move_mowers

import mower.mower.domain.Mower
import mower.mower.domain.Surface
import mower.mower.domain.value_object.*
import java.util.*

private const val NEW_LINE = "\n"

class MoveMowers constructor(private val instructionsProvider: InstructionsProvider) {

    fun execute(instructions: Array<String>): String {

        instructionsProvider.load(instructions)

        val results: MutableList<String> = mutableListOf()
        val surface: Surface = buildSurface(instructionsProvider.surface())

        var index = 0
        while (index < instructionsProvider.totalMowers()) {
            results.add(applyInstructions(index, surface))
            index ++
        }

        return printResult(results)
    }

    private fun applyInstructions(index: Int, surface: Surface): String {
        val mower = buildMower(instructionsProvider, index)
        val movements = instructionsProvider.mowerInstructions(index)

        movements.forEach {
            mower.move(MowerMovement.build(it.toString()), surface)
        }

        return mower.mowerPosition().positionAsString()
    }

    private fun printResult(results: MutableList<String>): String {
        var printableResults = ""

        results.forEach {
            printableResults += it + NEW_LINE
        }

        return printableResults.dropLast(1)
    }

    private fun buildSurface(surfaceData: String):Surface {
        val xSurfaceSize = surfaceData.split(" ")[0].toInt()
        val ySurfaceSize = surfaceData.split(" ")[1].toInt()

        return Surface.build(SurfaceXSize.build(xSurfaceSize), SurfaceYSize.build(ySurfaceSize))
    }

    private fun buildMower(instructionsProvider: InstructionsProvider, index: Int): Mower {
        val mowerPosition = instructionsProvider.mowerPosition(index)

        return Mower.build(
            MowerId.build(UUID.randomUUID().toString()),
            mowerPosition
        )
    }
}