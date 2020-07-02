package io.github.davidmerrick.adventofcode2019.day2

enum class OpCode(val value: Int) {
    ADD(1),
    MULT(2),
    HALT(99);

    companion object {
        private val map = OpCode.values().associateBy(OpCode::value)
        fun fromInt(type: Int) = map[type]
    }
}

