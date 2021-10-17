package utils

import Matrix

infix fun IntArray.xorPlus(array: IntArray) = mapIndexed { index: Int, item: Int ->
    item xor array[index]
}.toIntArray()

fun IntArray.pivotIndex() = toList().indexOfFirst { it == 1 }

infix fun IntArray.multiply(matrix: Matrix) : IntArray {
    val result = IntArray(matrix.colCount)
    (0 until matrix.colCount).forEach { column ->
        val currColumn = matrix.getColumn(column)
        result[column] = (this multiply currColumn).toIntArray().xorSum()
    }
    return result
}

infix fun IntArray.multiply(other: IntArray) = mapIndexed { index, item -> item * other[index]  }

fun IntArray.xorSum() = reduce { acc, i -> acc xor i }

