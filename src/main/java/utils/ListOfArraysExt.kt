package utils

import Matrix

fun List<IntArray>.toMatrix() = Matrix(size, this[0].size).also { result ->
    (0 until size).forEach { i -> result.setRow(i, this[i]) }
}

