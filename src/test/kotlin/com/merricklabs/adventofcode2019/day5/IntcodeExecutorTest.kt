package com.merricklabs.adventofcode2019.day5

import io.kotlintest.shouldBe
import org.testng.annotations.Test

class IntcodeExecutorTest {

    @Test
    fun `Test multiplication input with parameters`(){
        val program = mutableListOf(1002, 4, 3, 4, 33)
        val executor = IntcodeExecutor(program, 0)
        executor.execute()
        program[4] shouldBe 99
    }

    @Test
    fun `Test input`(){
        val program = mutableListOf(1101,100,-1,4,0)
        val executor = IntcodeExecutor(program, 0)
        executor.execute()
        program[4] shouldBe 99
    }

    @Test(description = "This should output whatever the input is")
    fun `Test input 2`(){
        val program = mutableListOf(3,0,4,0,99)
        val executor = IntcodeExecutor(program, 5)
        executor.execute()
    }

    @Test
    fun `Run test input`(){
        val program = this::class.java.getResourceAsStream("input.txt")
                .bufferedReader()
                .readText()
                .split(",")
                .map { it.toInt() }
                .toMutableList()
        val executor = IntcodeExecutor(program, 1)
        executor.execute()
    }

    @Test
    fun `Test for equals to`(){
        val program = mutableListOf(3,9,8,9,10,9,4,9,99,-1,8)
        val executor = IntcodeExecutor(program, 8)
        val output = executor.execute()
        output.toInt() shouldBe 1
    }

    @Test
    fun `Negative test for equals to`(){
        val program = mutableListOf(3,9,8,9,10,9,4,9,99,-1,8)
        val executor = IntcodeExecutor(program, 7)
        val output = executor.execute()
        output.toInt() shouldBe 0
    }

    @Test
    fun `Test for input less than 8`(){
        val program = mutableListOf(3,9,7,9,10,9,4,9,99,-1,8)
        val executor = IntcodeExecutor(program, 7)
        val output = executor.execute()
        output.toInt() shouldBe 1
    }

    @Test
    fun `Negative test for input less than 8`(){
        val program = mutableListOf(3,9,7,9,10,9,4,9,99,-1,8)
        val executor = IntcodeExecutor(program, 9)
        val output = executor.execute()
        output.toInt() shouldBe 0
    }

    @Test
    fun `Immediate mode test for equal to 8`(){
        val program = mutableListOf(3,3,1108,-1,8,3,4,3,99)
        val executor = IntcodeExecutor(program, 8)
        val output = executor.execute()
        output.toInt() shouldBe 1
    }

    @Test
    fun `Negative immediate mode test for equal to 8`(){
        val program = mutableListOf(3,3,1108,-1,8,3,4,3,99)
        val executor = IntcodeExecutor(program, 7)
        val output = executor.execute()
        output.toInt() shouldBe 0
    }

    @Test
    fun `Run test input for part 2`(){
        val program = this::class.java.getResourceAsStream("input.txt")
                .bufferedReader()
                .readText()
                .split(",")
                .map { it.toInt() }
                .toMutableList()
        val executor = IntcodeExecutor(program, 5)
        val output = executor.execute()
        println(output)
    }
}