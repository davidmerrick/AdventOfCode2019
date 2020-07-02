package io.github.davidmerrick.adventofcode2019.day5

import io.github.davidmerrick.adventofcode2019.day5.OpCode.ADD
import io.github.davidmerrick.adventofcode2019.day5.OpCode.EQUALS
import io.github.davidmerrick.adventofcode2019.day5.OpCode.INPUT
import io.github.davidmerrick.adventofcode2019.day5.OpCode.JUMP_IF_FALSE
import io.github.davidmerrick.adventofcode2019.day5.OpCode.JUMP_IF_TRUE
import io.github.davidmerrick.adventofcode2019.day5.OpCode.LESS_THAN
import io.github.davidmerrick.adventofcode2019.day5.OpCode.MULT
import io.github.davidmerrick.adventofcode2019.day5.OpCode.OUTPUT

data class Instruction(
        val header: InstructionHeader,
        val params: List<Int>,
        val input: Int?,
        val program: MutableList<Int>
) {
    fun execute(): ExecutionResult {
        return when (header.opCode) {
            MULT -> multiply()
            ADD -> add()
            INPUT -> input()
            OUTPUT -> output()
            JUMP_IF_TRUE -> jumpIfTrue()
            JUMP_IF_FALSE -> jumpIfFalse()
            LESS_THAN -> lessThan()
            EQUALS -> equals()
            else -> throw UnsupportedOperationException("Opcode ${header.opCode} not supported.")
        }
    }

    private fun jumpIfTrue(): ExecutionResult {
        val firstParam = getParam(0)
        return if (firstParam != 0) {
            val secondParam = getParam(1)
            ExecutionResult(jumpAddr = secondParam)
        } else {
            ExecutionResult()
        }
    }

    private fun jumpIfFalse(): ExecutionResult {
        val firstParam = getParam(0)
        return if (firstParam == 0) {
            val secondParam = getParam(1)
            ExecutionResult(jumpAddr = secondParam)
        } else {
            ExecutionResult()
        }
    }

    private fun lessThan(): ExecutionResult {
        val firstParam = getParam(0)
        val secondParam = getParam(1)
        val thirdParam = params[2]
        program[thirdParam] = if (firstParam < secondParam) {
            1
        } else {
            0
        }
        return ExecutionResult()
    }

    private fun equals(): ExecutionResult {
        val firstParam = getParam(0)
        val secondParam = getParam(1)
        val thirdParam = params[2]
        program[thirdParam] = if (firstParam == secondParam) {
            1
        } else {
            0
        }
        return ExecutionResult()
    }

    private fun multiply(): ExecutionResult {
        val firstParam = getParam(0)
        val secondParam = getParam(1)

        // Parameters that an instruction writes to
        // will never be in immediate mode.
        val thirdParam = params[2]
        program[thirdParam] = firstParam * secondParam
        return ExecutionResult()
    }

    private fun input(): ExecutionResult {
        program[params[0]] = input!!
        return ExecutionResult()
    }

    private fun output(): ExecutionResult {
        val output = program[params[0]]
        return ExecutionResult(output = output)
    }

    private fun add(): ExecutionResult {
        val firstParam = getParam(0)
        val secondParam = getParam(1)
        val thirdParam = params[2]

        // Parameters that an instruction writes to
        // will never be in immediate mode.
        program[thirdParam] = firstParam + secondParam
        return ExecutionResult()
    }

    private fun getParam(index: Int) = if (header.paramModes[index] == 0) {
        program[params[index]]
    } else {
        params[index]
    }
}
