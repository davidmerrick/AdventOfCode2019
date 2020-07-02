package io.github.davidmerrick.adventofcode2019.day7

import io.github.davidmerrick.adventofcode2019.day7.AmplifierExecutor.Companion.generatePhasePermutations
import io.github.davidmerrick.adventofcode2019.testutil.toIntCodeProgram
import io.kotlintest.shouldBe
import org.testng.annotations.Test

class AmplifierExecutorTest {

    @Test
    fun `Phase setting 4,3,2,1,0`() {
        val program = mutableListOf(3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0)
        val phaseSettings = listOf(4, 3, 2, 1, 0)

        val executor = AmplifierExecutor(program, phaseSettings)
        val output = executor.execAmplifiers()

        output shouldBe 43_210
    }

    @Test
    fun `Phase setting 0,1,2,3,4`() {
        val program = mutableListOf(3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0)
        val phaseSettings = listOf(0, 1, 2, 3, 4)
        val executor = AmplifierExecutor(program, phaseSettings)
        val output = executor.execAmplifiers()

        output shouldBe 54_321
    }

    @Test
    fun `Find max output of program`() {
        val program = mutableListOf(3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0)
        val combos = generatePhasePermutations(0, 4)

        val output = combos
                .mapNotNull { AmplifierExecutor(program, it).execAmplifiers() }
                .max()

        output shouldBe 54_321
    }

    @Test
    fun `Max output test 3`() {
        val program = mutableListOf(3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33,
                1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0)
        val combos = generatePhasePermutations(0, 4)

        val output = combos
                .mapNotNull { AmplifierExecutor(program, it).execAmplifiers() }
                .max()

        output shouldBe 65210
    }

    @Test
    fun `Test with my input`() {
        val program = this::class.java.getResourceAsStream("input.txt")
                .toIntCodeProgram()

        val combinations = generatePhasePermutations(0, 4)

        val max = combinations
                .mapNotNull { AmplifierExecutor(program, it).execAmplifiers() }
                .max()
        println(max)
    }

    @Test
    fun `Debugging part 2`() {
        val program = listOf(3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26,
                27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5)
        val amplifier = Amplifier(9, program)

        var result = 0
        result = amplifier.step(result)!!
        result shouldBe 5

        result = amplifier.step(result)!!
        result shouldBe 15
    }

    @Test
    fun `Part 2 test 1`() {
        val program = listOf(3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26,
                27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5)
        val combos = listOf(9, 8, 7, 6, 5)
        val executor = AmplifierExecutor(program, combos)

        val output = executor.execWithFeedback()

        output shouldBe 139629729
    }

    @Test
    fun `Max from part 2 test 1`() {
        val program = listOf(3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26,
                27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5)
        val combos = generatePhasePermutations(5, 9)
        val max = combos
                .mapNotNull { AmplifierExecutor(program, it).execWithFeedback() }
                .max()

        max shouldBe 139629729
    }

    @Test
    fun `Part 2 my input`() {
        val program = this::class.java.getResourceAsStream("input.txt")
                .toIntCodeProgram()

        val combinations = generatePhasePermutations(5, 9)

        val max = combinations
                .mapNotNull { AmplifierExecutor(program, it).execWithFeedback() }
                .max()
        println(max)
    }
}
