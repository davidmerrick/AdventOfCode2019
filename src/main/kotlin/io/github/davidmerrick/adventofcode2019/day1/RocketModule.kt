package io.github.davidmerrick.adventofcode2019.day1

data class RocketModule(val mass: Int) {
    fun getFuel() = mass / 3 - 2
}
