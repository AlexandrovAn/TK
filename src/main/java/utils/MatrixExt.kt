package utils

import Matrix

fun Matrix.swapRows(firstRow: Int, secondRow: Int) {
    val rowHolder = this.getRow(firstRow)
    this.setRow(firstRow, this.getRow(secondRow))
    this.setRow(secondRow, rowHolder)
}

fun Matrix.setRow(row: Int, array: IntArray): Unit =
    (0 until colCount).forEach { i -> this[row, i] = array[i] }


infix fun IntArray.xorPlus(array: IntArray) = mapIndexed { index: Int, item: Int ->
    item xor array[index]
}.toIntArray()

