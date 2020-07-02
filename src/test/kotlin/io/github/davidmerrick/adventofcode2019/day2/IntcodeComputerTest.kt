package io.github.davidmerrick.adventofcode2019.day2

import io.kotlintest.shouldBe
import org.testng.annotations.Test

object IntcodeComputerTest {

    @Test
    fun `Test input`() {
        val input = "1,1,1,4,99,5,6,0,99"
        var program = input.split(",").map { it.toInt() }.toMutableList()
        val computer = IntcodeComputer(program)
        val result = computer.compute()
        result.joinToString(",") shouldBe "30,1,1,4,2,5,6,0,99"
    }

    @Test
    fun `Test input 2`() {
        val input = "2,4,4,5,99,0"
        var program = input.split(",").map { it.toInt() }.toMutableList()
        val computer = IntcodeComputer(program)
        val result = computer.compute()
        result.joinToString(",") shouldBe "2,4,4,5,99,9801"
    }

    @Test
    fun `Get result at position 0`() {
        val program = this::class.java.getResourceAsStream("input.txt")
            .bufferedReader()
            .readText()
            .split(",")
            .map { it.toInt() }
            .toMutableList()
        val computer = IntcodeComputer(program)
        val result = computer.compute()
        println(result[0])
    }

    @Test
    fun `Part 2, Get inputs that produce output 19690720`() {
        // I'm just going to take a brute-force approach

        val desiredResult = 19690720

        val initialProgram = this::class.java.getResourceAsStream("input.txt")
            .bufferedReader()
            .readText()
            .split(",")
            .map { it.toInt() }

        var noun = 0
        var verb = 0
        var found = false
        val range = initialProgram.indices
        outerLoop@ for (i in range) {
            for (j in range) {
                val program = initialProgram.toMutableList()
                program[1] = i
                program[2] = j
                val computer = IntcodeComputer(program)
                val result = computer.compute()
                if (result[0] == desiredResult) {
                    found = true
                    noun = i
                    verb = j
                    break@outerLoop
                }
            }
        }

        if (found) {
            println("Found answer: ${100 * noun + verb}")
        } else {
            println("No answer found")
        }
    }
}
