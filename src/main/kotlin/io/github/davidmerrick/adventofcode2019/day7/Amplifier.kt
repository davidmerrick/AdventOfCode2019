package io.github.davidmerrick.adventofcode2019.day7

import io.github.davidmerrick.adventofcode2019.day5.ExecutionState.HALTED
import io.github.davidmerrick.adventofcode2019.day5.IntcodeExecutor
import java.util.ArrayDeque

class Amplifier(phase: Int, program: List<Int>) {

    private var _halted = false
    val halted
        get() = _halted

    private var _lastOutput: Int? = null
    val lastOutput
        get() = _lastOutput

    private val intcodeExecutor = IntcodeExecutor(program, ArrayDeque(listOf(phase)))

    fun step(signal: Int?): Int? {
        signal?.let {
            this.intcodeExecutor.input.add(signal)
            val result = this.intcodeExecutor.execute()
            if (this.intcodeExecutor.state == HALTED) {
                _halted = true
            } else {
                _lastOutput = result
            }
            return result
        }

        return null
    }
}
