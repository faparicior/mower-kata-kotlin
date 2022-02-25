import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

private const val EXPECTED_OUTPUT = "1 3 N\n5 1 E"
private const val FILENAME: String = "src/test/kotlin/mower/mower/application/movemowers/fixtures/instructions.txt"

internal class MainKtIntegrationTest {

    @Test
    fun `Should process input from command line`() {
        val myOut = ByteArrayOutputStream()
        System.setOut(PrintStream(myOut))

        main(arrayOf(FILENAME))

        Assertions.assertThat(myOut.toString()).isEqualTo(EXPECTED_OUTPUT)
    }
}
