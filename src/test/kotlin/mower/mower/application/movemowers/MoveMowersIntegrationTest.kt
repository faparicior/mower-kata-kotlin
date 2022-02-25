package mower.mower.application.movemowers

import mower.mower.infrastructure.instructionsprovider.FlatFileInstructionsProvider
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

private const val FILENAME: String = "src/test/kotlin/mower/mower/application/movemowers/fixtures/instructions.txt"

internal class MoveMowersIntegrationTest {

    @Test
    fun `Should process input file and do output results`() {
        val instructions= MoveMowersCommand(FILENAME)
        val instructionsProvider = FlatFileInstructionsProvider()
        val moveMowers = MoveMowers(instructionsProvider)

        val response = moveMowers.execute(instructions)

        assertThat(response.response).isEqualTo("1 3 N\n5 1 E")
    }
}