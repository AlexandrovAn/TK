package utils

import Matrix

fun identityMatrix(size: Int): List<List<Int>> {
    val matrix = Matrix(size, size)
    (0 until size).forEach { i ->
        val intArray = IntArray(size)
        intArray[i] = 1
        matrix.setRow(i, intArray)
    }
    return matrix.to2DList()
}