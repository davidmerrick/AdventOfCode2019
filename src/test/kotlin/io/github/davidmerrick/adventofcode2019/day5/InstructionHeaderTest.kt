package io.github.davidmerrick.adventofcode2019.day5

import io.github.davidmerrick.adventofcode2019.day5.OpCode.ADD
import io.github.davidmerrick.adventofcode2019.day5.OpCode.MULT
import io.kotlintest.shouldBe
import org.testng.annotations.Test

class InstructionHeaderTest {

    @Test
    fun `Parse example`() {
        val header = InstructionHeader(1002)
        header.opCode shouldBe MULT
        header.numParams shouldBe 3
        header.paramModes.size shouldBe 3
        header.paramModes[0] shouldBe 0
        header.paramModes[1] shouldBe 1
    }

    @Test
    fun `Left-pad instructions`() {
        val header = InstructionHeader(101)
        header.opCode shouldBe ADD
        header.paramModes.size shouldBe 3
        header.paramModes[0] shouldBe 1
        header.paramModes[1] shouldBe 0
    }
}
