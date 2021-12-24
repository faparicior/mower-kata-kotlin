package mower.mower.application.move_mowers

import mower.mower.domain.Mower
import mower.mower.domain.Surface
import mower.mower.domain.value_object.*
import java.util.*

class MoveMowers {

    fun execute(instructions: Array<String>): String {
        val results: MutableList<String> = mutableListOf()
        val surface: Surface = buildSurface(instructions[0])

        var index = 1
        while (index <= instructions.size - 1) {
            val mower = buildMower(instructions[index])
            val movements = instructions[index + 1].toCharArray()

            movements.forEach {
                mower.move(MowerMovement.build(it.toString()), surface)
            }

            results.add(mower.mowerPosition().positionAsString())
            index += 2
        }

        return printResult(results)
    }

    private fun printResult(results: MutableList<String>): String {
        var printableResults = ""

        results.forEach {
            printableResults += it + "\n"
        }

        return printableResults.dropLast(1)
    }

    private fun buildSurface(surfaceData: String):Surface {
        val xSurfaceSize =  surfaceData.split(" ")[0].toInt()
        val ySurfaceSize =  surfaceData.split(" ")[1].toInt()

        return Surface.build(SurfaceXSize.build(xSurfaceSize), SurfaceYSize.build(ySurfaceSize))
    }

    private fun buildMower(mowerPositionData: String): Mower {
        val xMowerPosition = XMowerPosition.build(mowerPositionData.split(" ")[0].toInt())
        val yMowerPosition = YMowerPosition.build(mowerPositionData.split(" ")[1].toInt())
        val mowerOrientation = MowerOrientation.build(mowerPositionData.split(" ")[2])

        val mowerPosition = MowerPosition.build(xMowerPosition, yMowerPosition, mowerOrientation)

        return Mower.build(
            MowerId.build(UUID.randomUUID().toString()),
            mowerPosition
        )
    }
}