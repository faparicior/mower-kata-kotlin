package mower.mower.value_object

import mower.mower.exception.InvalidMowerIdException
import java.util.*

class MowerId private constructor(val value: String){

    init {
        try {
            UUID.fromString(value)
        } catch (exception: Exception)
        {
            throw InvalidMowerIdException.withValue(value)
        }
    }

    companion object {
        fun build(value: String): MowerId {
            return MowerId(value)
        }
    }
}