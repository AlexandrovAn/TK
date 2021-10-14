import utils.*

fun Matrix.ref(): Matrix {
    val result = Matrix(this)
    var currentRow = 0

    (0 until colCount).forEach { i ->

        val pivot = result.refFindPivot(i, currentRow)
        if (pivot >= currentRow) {
            if (pivot != currentRow) result.swapRows(currentRow, pivot)
            result.refCleanOnes(i, currentRow)
            currentRow++
        }
    }
    return result
}

fun Matrix.refFindPivot(columnIndex: Int, row: Int) = if (row == rowCount) -1
else when (val pivot = getColumn(columnIndex).slice(row until rowCount).indexOfFirst { it == 1 }) {
    -1 -> pivot
    else -> pivot + row
}

fun Matrix.refCleanOnes(i: Int, index: Int) =
    columnFilter(
        column = i,
        underRowIndex = index
    ).toIntArray().forEach { rowIndex ->
        this.setRow(rowIndex, this.getRow(rowIndex) xorPlus this.getRow(index))
    }

