package mower.mower.infrastructure.instructionsprovider

import mower.mower.domain.value_object.*
import org.assertj.core.api.Assertions.assertThat
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.test.BeforeTest
import kotlin.test.Test

private const val SURFACE = "5 5"
private const val MOWER_POSITION_1 = "1 2 N"
private const val MOWER_MOVEMENTS_1 = "LFLFLFLFF"
private const val MOWER_POSITION_2 = "3 3 E"
private const val MOWER_MOVEMENTS_2 = "FFRFFRFRRF"

internal class FlatFileInstructionsProviderTest {

    private val instructions:Array<String> = arrayOf(
        SURFACE,
        MOWER_POSITION_1,
        MOWER_MOVEMENTS_1,
        MOWER_POSITION_2,
        MOWER_MOVEMENTS_2
    )

    private val flatFileInstructionsProvider: FlatFileInstructionsProvider = FlatFileInstructionsProvider()

    @BeforeTest
    fun setUp() {
        flatFileInstructionsProvider.load(instructions)
    }

    @Test
    fun `Should load instructions`() {
        flatFileInstructionsProvider.load(instructions)

        val expectedInstructions:Array<String> = arrayOf(
            MOWER_POSITION_1,
            MOWER_MOVEMENTS_1,
            MOWER_POSITION_2,
            MOWER_MOVEMENTS_2
        )

        val field = FlatFileInstructionsProvider::class.memberProperties.find { it.name == "instructions" }
        field?.let {
            it.isAccessible = true
            val value = it.get(flatFileInstructionsProvider)
            assertThat(value).isEqualTo(expectedInstructions.toMutableList())
        }

        val expectedSurface: String = SURFACE
        val surface = flatFileInstructionsProvider.surface()

        assertThat(surface).isEqualTo(expectedSurface)
    }

    @Test
    fun `Should return a Surface`() {
        val surface = flatFileInstructionsProvider.surface()
        val expectedSurface = SURFACE

        assertThat(surface).isEqualTo(expectedSurface)
    }

    @Test
    fun `Should return total mowers`() {
        val totalMowers = flatFileInstructionsProvider.totalMowers()

        assertThat(totalMowers).isEqualTo(2)
    }

    @Test
    fun `Should return mowerPosition by index`() {
        val mowerPosition = flatFileInstructionsProvider.mowerPosition(0)
        val expectedMowerPosition: MowerPosition = MowerPosition.build(
            XMowerPosition.build(1),
            YMowerPosition.build(2),
            MowerOrientation.build("N")
        )

        assertThat(mowerPosition).usingRecursiveComparison().isEqualTo(expectedMowerPosition)

        val mowerPosition2 = flatFileInstructionsProvider.mowerPosition(1)
        val expectedMowerPosition2: MowerPosition = MowerPosition.build(
            XMowerPosition.build(3),
            YMowerPosition.build(3),
            MowerOrientation.build("E")
        )

        assertThat(mowerPosition2).usingRecursiveComparison().isEqualTo(expectedMowerPosition2)
    }

    @Test
    fun `Should return mower movements by index`() {
        val mowerMovements: String = flatFileInstructionsProvider.mowerInstructions(0)
        assertThat(mowerMovements).isEqualTo(MOWER_MOVEMENTS_1)

        val mowerMovements2: String = flatFileInstructionsProvider.mowerInstructions(1)
        assertThat(mowerMovements2).isEqualTo(MOWER_MOVEMENTS_2)
    }
}