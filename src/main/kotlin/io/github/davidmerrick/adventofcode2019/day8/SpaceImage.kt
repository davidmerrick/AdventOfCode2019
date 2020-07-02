package io.github.davidmerrick.adventofcode2019.day8

private const val TRANSPARENT = 2
private const val BLACK = 0

data class SpaceImage(val digits: String, val width: Int, val height: Int) {

    val layers by lazy {
        digits.split("")
                .filter { it.isNotEmpty() }
                .map { it.toInt() }
                .chunked(width * height)
    }

    fun render(): List<Int> {
        val rendered = mutableListOf<Int>()
        for (i in 0 until width * height) {
            val pixels = getPixels(i)
            rendered.add(pixels.renderPixel())
        }

        return rendered
    }

    private fun getPixels(i: Int): List<Int> {
        return layers.map { it[i] }
    }
}

private fun List<Int>.renderPixel() = this.first { it != TRANSPARENT }
