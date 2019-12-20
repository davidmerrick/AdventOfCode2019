package com.merricklabs.adventofcode2019.day5

import com.merricklabs.adventofcode2019.day5.OpCode.HALT

class IntcodeExecutor(private val program: MutableList<Int>) {

    private var instructionPointer = 0

    fun execute() {
        println("Instructions:")

        while (instructionPointer < program.size) {
            val header = InstructionHeader(program[instructionPointer])
            println(program.subList(instructionPointer, instructionPointer + header.numParams + 1))
            if (header.opCode == HALT) {
                return
            }
            instructionPointer += header.numParams + 1
        }
    }
}