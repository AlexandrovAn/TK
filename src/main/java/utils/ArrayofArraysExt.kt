package utils

fun Array<Array<Int>>.toListOfList() = (0 until size).map { this[it].toList() }