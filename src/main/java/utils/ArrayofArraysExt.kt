package utils

fun Array<Array<Int>>.to2DList() = (0 until size).map { this[it].toList() }