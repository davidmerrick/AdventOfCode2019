package io.github.davidmerrick.adventofcode2019.day5

enum class OpCode(val value: Int) {
    ADD(1),
    MULT(2),
    INPUT(3),
    OUTPUT(4),
    JUMP_IF_TRUE(5),
    JUMP_IF_FALSE(6),
    LESS_THAN(7),
    EQUALS(8),
    HALT(99);

    companion object {
        private val map = values().associateBy(OpCode::value)
        fun fromInt(type: Int) = map[type]
    }
}

