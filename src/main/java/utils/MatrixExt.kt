package utils

import Matrix

fun Matrix.swapRows(firstRow: Int, secondRow: Int) {
    val rowHolder = this.getRow(firstRow)
    this.setRow(firstRow, this.getRow(secondRow))
    this.setRow(secondRow, rowHolder)
}

fun Matrix.setRow(row: Int, array: IntArray): Unit =
    (0 until colCount).forEach { i -> this[row, i] = array[i] }

fun Matrix.columnFilter(
    column: Int,
    underRowIndex: Int = -1,
    aboveRowIndex: Int = rowCount
) = this.getColumn(column).toList()
    .mapIndexedNotNull { index, item ->
        when (item) {
            1 -> index
            else -> null
        }
    }.filter { it in (underRowIndex + 1) until aboveRowIndex }

fun Matrix.rowZeroFilter(): Matrix {
    var result = Matrix(this)
    (0 until rowCount).forEach { row ->
        if (this.getRow(row).all { it == 0 })
            result = result.deleteRow(row)
    }
    return result
}

fun Matrix.deleteRow(row: Int): Matrix {
    val result = Matrix(rowCount - 1, colCount)
    var resultIndex = 0
    (0 until rowCount - 1).forEach { i ->
        if (i != row) {
            result.setRow(resultIndex, getRow(i))
            ++resultIndex
        }
    }
    return result
}

fun Matrix.to2DList() = (0 until rowCount).map { getThisRow(it).toList() }

