package io.github.davidmerrick.adventofcode2019.day5

import io.github.davidmerrick.adventofcode2019.day5.ExecutionState.HALTED
import io.github.davidmerrick.adventofcode2019.day5.ExecutionState.RUNNING
import io.github.davidmerrick.adventofcode2019.day5.OpCode.HALT
import io.github.davidmerrick.adventofcode2019.day5.OpCode.INPUT
import java.util.ArrayDeque
import java.util.Deque

open class IntcodeExecutor(program: List<Int>, val input: Deque<Int> = ArrayDeque()) {
    // Defensively make a copy
    private val _program = program.toMutableList()
    val program
        get() = _program.toList()

    private var _instructionPointer = 0

    private var _state = RUNNING
    val state
        get() = _state

    fun execute(): Int? {

        while (_instructionPointer < _program.size) {

            // Read
            val header = InstructionHeader(_program[_instructionPointer])
            if (header.opCode == HALT) {
                _state = HALTED
                break
            }

            val currentInput = if (header.opCode == INPUT && input.isNotEmpty()) {
                input.removeFirst()
            } else {
                null
            }

            val params = _program.subList(_instructionPointer + 1, _instructionPointer + 1 + header.numParams)
            val instruction = Instruction(header, params, currentInput, _program)

            // Compute/store. Might want to break these out.
            val result = instruction.execute()
            _instructionPointer = result.jumpAddr?.let {
                it
            } ?: _instructionPointer + header.numParams + 1

            // Output
            result.output?.let {
                return it
            }
        }

        return null
    }
}
