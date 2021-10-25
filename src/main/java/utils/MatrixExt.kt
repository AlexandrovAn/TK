package utils

import Matrix
import kotlin.random.Random

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
    (0 until rowCount).forEach { i ->
        if (i != row) {
            result.setRow(resultIndex, getRow(i))
            ++resultIndex
        }
    }
    return result
}

fun Matrix.to2DList() = (0 until rowCount).map { getThisRow(it).toList() }

infix fun Matrix.multiply(other: Matrix): Matrix {
    val resultMatrix = Matrix(rowCount, other.colCount)
    (0 until resultMatrix.rowCount).forEach { row ->
        val multiplyResult = getRow(row) multiply other
        resultMatrix.setRow(row, multiplyResult)
    }
    return resultMatrix
}

fun Matrix.sum() = to2DList().reduce { acc, list -> acc + list.sum() }.sum()

fun Matrix.codeDistance() = to2DList().map { it.sum() }.minOrNull() ?: -1

fun Matrix.generateError(t: Int): IntArray {
    val error = IntArray(colCount)
    var length = t
    while (length > 0) {
        val i = Random.nextInt(0, error.size)
        if (error[i] != 1) {
            error[i] = 1
            length = length.minus(1)
        }
    }
    return error
}

fun Matrix.addError(error: IntArray) {
    (0 until rowCount).forEach { row ->
        setRow(row, getRow(row) xorPlus error)
    }
}

fun Matrix.verifyErroredMatrix(verificationMatrix: Matrix) {
    println("Проверка:")
    (0 until rowCount).forEach { row ->
        println("строка ${row + 1} * H = ${(getRow(row) multiply verificationMatrix).toList()}")
    }
}

fun Matrix.findSameRow(row: IntArray): Int {
    to2DList().forEachIndexed { index, currRow ->
        if (row.toList() == currRow) return index
    }
    return -1
}

fun Matrix.generateAllXorCombinations(): Array<Array<IntArray>> {
    val result = Array<Array<IntArray>>(rowCount) { Array<IntArray>(rowCount) { IntArray(colCount) { 0 } } }
    (0 until rowCount).forEach { i ->
        (0 until rowCount).forEach { j ->
            result[i][j] = getRow(i) xorPlus getRow(j)
        }
    }
    return result
}

