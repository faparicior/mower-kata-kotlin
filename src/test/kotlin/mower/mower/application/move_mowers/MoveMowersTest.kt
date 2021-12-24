package mower.mower.application.move_mowers

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

internal class MoveMowersTest {

    @Test
    fun `Should process input file and do output results`() {
        val instructions:Array<String> = arrayOf("5 5", "1 2 N", "LFLFLFLFF", "3 3 E", "FFRFFRFRRF")
        val moveMowers = MoveMowers()

        val response = moveMowers.execute(instructions)

        assertThat(response).isEqualTo("1 3 N\n5 1 E")
    }
}