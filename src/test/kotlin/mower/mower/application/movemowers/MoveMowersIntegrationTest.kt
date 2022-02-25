package mower.mower.application.movemowers

import main
import org.assertj.core.api.Assertions.assertThat
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test

const val fileName: String = "src/test/kotlin/mower/mower/application/movemowers/fixtures/instructions.txt"

internal class MoveMowersIntegrationTest {

    @Test
    fun `Should process input from command line`() {
        val myOut = ByteArrayOutputStream()
        System.setOut(PrintStream(myOut))

        main(arrayOf(fileName))

        assertThat(myOut.toString()).isEqualTo("1 3 N\n5 1 E")
    }
}