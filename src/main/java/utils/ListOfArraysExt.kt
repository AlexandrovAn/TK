package utils

import Matrix

fun List<IntArray>.toMatrix(): Matrix {
    val matrix = Matrix(size, this[0].size)
    (0 until size).forEach { i ->
        matrix.setRow(i, this[i])
    }
    return matrix
}