package com.merricklabs.adventofcode2019.day8

import io.kotlintest.matchers.collections.shouldContainInOrder
import io.kotlintest.shouldBe
import io.kotlintest.days
import org.testng.annotations.Test

class SpaceImageTest {

    @Test
    fun `Get layers test`() {
        val digits = "123456789012"
        val image = SpaceImage(digits, 3, 2)
        val layers = image.layers
        layers.size shouldBe 2
        layers[0].shouldContainInOrder(1, 2, 3, 4, 5, 6)
        layers[1].shouldContainInOrder(7, 8, 9, 0, 1, 2)
    }

    @Test
    fun `Test with my input`() {
        val digits = this::class.java.getResourceAsStream("input.txt")
            .bufferedReader()
            .readText()
        val image = SpaceImage(digits, 25, 6)
        val layers = image.layers
        val layer = layers.minBy { layer -> layer.count { it == 0 } }!!
        println(layer.count { it == 1 } * layer.count { it == 2 })
    }

    @Test
    fun `Render simple image`() {
        val digits = "0222112222120000"
        val width = 2
        val height = 2
        val image = SpaceImage(digits, width, height)
        val rendered = image.render()
        rendered.size shouldBe width * height
        rendered[0] shouldBe 0
        rendered[1] shouldBe 1
        rendered[2] shouldBe 1
        rendered[3] shouldBe 0
    }

    @Test
    fun `Render my input`() {
        val width = 25
        val height = 6
        val digits = this::class.java.getResourceAsStream("input.txt")
            .bufferedReader()
            .readText()
        val image = SpaceImage(digits, width, height)
        image.render().chunked(width).forEach { println(it) }

        // My answer was CFCUG. Helped to read it by replacing 0s with dashes
    }
}
