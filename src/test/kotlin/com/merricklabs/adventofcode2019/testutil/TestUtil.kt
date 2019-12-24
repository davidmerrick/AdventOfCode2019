package com.merricklabs.adventofcode2019.testutil

import java.io.InputStream

fun InputStream.toIntCodeProgram() = this.bufferedReader()
        .readText()
        .split(",")
        .map { it.toInt() }
        .toMutableList()