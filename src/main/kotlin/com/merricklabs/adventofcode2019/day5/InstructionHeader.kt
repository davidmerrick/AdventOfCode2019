package com.merricklabs.adventofcode2019.day5

import com.merricklabs.adventofcode2019.day5.OpCode.ADD
import com.merricklabs.adventofcode2019.day5.OpCode.EQUALS
import com.merricklabs.adventofcode2019.day5.OpCode.HALT
import com.merricklabs.adventofcode2019.day5.OpCode.INPUT
import com.merricklabs.adventofcode2019.day5.OpCode.JUMP_IF_FALSE
import com.merricklabs.adventofcode2019.day5.OpCode.JUMP_IF_TRUE
import com.merricklabs.adventofcode2019.day5.OpCode.LESS_THAN
import com.merricklabs.adventofcode2019.day5.OpCode.MULT
import com.merricklabs.adventofcode2019.day5.OpCode.OUTPUT

private const val HEADER_LENGTH = 5

data class InstructionHeader(private val value: Int) {
    val opCode = OpCode.fromInt(value % 100)!!
    val numParams: Int
    val paramModes: List<Int>

    init {
        numParams = when (opCode) {
            MULT, ADD, LESS_THAN, EQUALS -> 3
            JUMP_IF_TRUE, JUMP_IF_FALSE -> 2
            INPUT, OUTPUT -> 1
            HALT -> 0
        }
        paramModes = value.toString()
                .padStart(HEADER_LENGTH, '0')
                .split("")
                .filter { it != "" }
                .map { it.toInt() }
                .subList(0, 3)
                .reversed()
    }
}