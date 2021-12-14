package mower.mower.value_object

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.Test

private const val MOVE_FORWARD: String = "F"
private const val INVALID_MOVEMENT: String = "J"

internal class MowerMovementTest
{
    @Test
    fun `Should be build`() {
        val movement = MowerMovement.build(MOVE_FORWARD)

        assertInstanceOf(MowerMovement::class.java, movement)
    }

    @ParameterizedTest
    @ValueSource(strings = [ "L", "R", "F"])
    fun `Should accept valid values`(value: String) {
        val movement = MowerMovement.build(value)

        assertThat(movement.value).isEqualTo(value)
    }

    @Test
    fun `Should throw exception for invalid movement`() {
        assertThrows(Exception::class.java) {
            MowerMovement.build(INVALID_MOVEMENT)
        }
    }
}