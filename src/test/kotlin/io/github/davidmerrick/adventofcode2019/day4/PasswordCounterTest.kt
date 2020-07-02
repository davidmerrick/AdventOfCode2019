package io.github.davidmerrick.adventofcode2019.day4

import io.kotlintest.shouldBe
import org.testng.annotations.Test

class PasswordCounterTest {
    @Test
    fun `Valid input`(){
        val input = 111111
        PasswordCounter.isIncreasing(input) shouldBe true
        PasswordCounter.hasDouble(input) shouldBe true
        PasswordCounter.meetsPart1Criteria(input) shouldBe true
    }

    @Test
    fun `Non-decreasing input`(){
        val input = 223450
        PasswordCounter.isIncreasing(input) shouldBe false
        PasswordCounter.hasDouble(input) shouldBe true
    }

    @Test
    fun `Input with no double`(){
        val input = 123789
        PasswordCounter.isIncreasing(input) shouldBe true
        PasswordCounter.hasDouble(input) shouldBe false
    }

    @Test
    fun `Test with my input`(){
        val passwordCounter = PasswordCounter(372304, 847060)
        println(passwordCounter.count())
    }

    @Test
    fun `Valid part 2 input`(){
        PasswordCounter.hasOnlyDouble(112233) shouldBe true
    }

    @Test
    fun `Valid part 2 input 2`(){
        PasswordCounter.hasOnlyDouble(111122) shouldBe true
    }

    @Test
    fun `Invalid part 2 input`(){
        PasswordCounter.hasOnlyDouble(123444) shouldBe false
    }

    @Test
    fun `Test with my input for part 2`(){
        val passwordCounter = PasswordCounter(372304, 847060)
        println(passwordCounter.countPart2())
    }
}
