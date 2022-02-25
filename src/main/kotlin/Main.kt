import mower.mower.application.movemowers.MoveMowers
import mower.mower.application.movemowers.MoveMowersCommand
import mower.mower.infrastructure.instructionsprovider.FlatFileInstructionsProvider

fun main(args: Array<String>){
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//    println("Processing instructions from: ${args.joinToString()}")

    val moveMowers = MoveMowers(FlatFileInstructionsProvider())
    val moveMowersCommand = MoveMowersCommand(args.first().toString())

    print(moveMowers.execute(moveMowersCommand).response)
}