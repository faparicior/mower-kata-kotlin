package mower.mower.value_object

import mower.mower.exception.InvalidMowerIdException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import kotlin.test.Test

private const val MOWER_ID = "7feafd5b-859f-4d47-88cc-7eaa2ec88c24"
private const val INVALID_MOWER_ID = "6feafd5b-k59f-4d47-88cc-7eaa2ec88c24"

internal class MowerIdTest
{
    @Test
    fun `Should be build` () {
        val mowerId = MowerId.build(MOWER_ID)

        assertThat(mowerId.value).isEqualTo(MOWER_ID)
    }

    @Test
    fun `Should throw exception for invalid UUID values` () {
        assertThrows(InvalidMowerIdException::class.java) {
            MowerId.build(INVALID_MOWER_ID)
        }
    }
}