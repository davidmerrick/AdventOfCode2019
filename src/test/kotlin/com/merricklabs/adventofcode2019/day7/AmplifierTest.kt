package com.merricklabs.adventofcode2019.day7

import com.merricklabs.adventofcode2019.day5.IntcodeExecutor
import com.merricklabs.adventofcode2019.testutil.toIntCodeProgram
import io.kotlintest.shouldBe
import org.paukov.combinatorics3.Generator
import org.testng.annotations.Test

class AmplifierTest {

    @Test
    fun `Phase setting 4,3,2,1,0`() {
        val program = mutableListOf(3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0)
        val phaseSettings = listOf(4, 3, 2, 1, 0)
        val output = execAmplifiers(program, phaseSettings)

        output shouldBe 43_210
    }

    @Test
    fun `Phase setting 0,1,2,3,4`() {
        val program = mutableListOf(3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0)
        val phaseSettings = listOf(0, 1, 2, 3, 4)
        val output = execAmplifiers(program, phaseSettings)

        output shouldBe 54_321
    }

    @Test
    fun `Find max output of program`() {
        val program = mutableListOf(3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0)
        val combos = generatePhaseCombinations()
        val output = combos
                .map { execAmplifiers(program, it) }
                .max()

        output shouldBe 54_321
    }

    @Test
    fun `Max output test 3`() {
        val program = mutableListOf(3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33,
                1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0)

        val combos = generatePhaseCombinations()
        val output = combos
                .map { execAmplifiers(program, it) }
                .max()

        output shouldBe 65210
    }

    @Test
    fun `Test with my input`() {
        val program = this::class.java.getResourceAsStream("input.txt")
                .toIntCodeProgram()

        val combinations = generatePhaseCombinations()

        val max = combinations
                .map { execAmplifiers(program, it) }
                .max()
        println(max)
    }

    private fun generatePhaseCombinations(): List<List<Int>> {
        // Phase settings are in the range [0,4]
        return Generator.permutation(0, 1, 2, 3, 4)
                .simple()
                .toList()
    }

    private fun execAmplifiers(program: MutableList<Int>,
                               phaseSettings: List<Int>): Int {
        var previousOutput = 0 // Initialize output to 0

        for (i in 0..4) {
            val input = listOf(phaseSettings[i], previousOutput)
            val executor = IntcodeExecutor(program, input)
            previousOutput = executor.execute().toInt()
        }

        return previousOutput
    }
}