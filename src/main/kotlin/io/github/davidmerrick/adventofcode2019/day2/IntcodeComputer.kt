package io.github.davidmerrick.adventofcode2019.day2

class IntcodeComputer(private val program: MutableList<Int>) {

    fun compute(): List<Int> {
        var index = 0
        loop@ while (true) {
            val current = program[index]

            // Read opcode
            val opcode = OpCode.fromInt(current)
            if (opcode == OpCode.HALT) {
                break
            }

            // Compute

            val addr1 = program[index + 1]
            val addr2 = program[index + 2]
            val operand1 = program[addr1]
            val operand2 = program[addr2]

            val result = when (opcode) {
                OpCode.ADD -> operand1 + operand2
                OpCode.MULT -> operand1 * operand2
                else -> throw RuntimeException("This should never happen")
            }

            // Store

            val storeAddr = program[index + 3]
            program[storeAddr] = result

            // Continue

            index += 4
        }

        return program
    }
}
