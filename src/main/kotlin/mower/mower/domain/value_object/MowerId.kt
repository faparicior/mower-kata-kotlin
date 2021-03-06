package mower.mower.domain.value_object

import mower.mower.domain.exception.InvalidMowerIdException
import java.util.*

@JvmInline
value class MowerId private constructor(val value: String){

    init {
        try {
            UUID.fromString(value)
        } catch (exception: Exception)
        {
            throw InvalidMowerIdException.withValue(value)
        }
    }

    companion object {
        @JvmStatic
        fun build(value: String): MowerId {
            return MowerId(value)
        }
    }
}