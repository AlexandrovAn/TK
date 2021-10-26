import utils.*

fun Matrix.ref() = Matrix(this).also { result ->
    var currentRow = 0
    (0 until colCount).forEach { i ->
        val pivot = result.refFindPivot(i, currentRow)
        if (pivot < currentRow) return@forEach
        if (pivot != currentRow) result.swapRows(currentRow, pivot)
        result.columnFilter(i, currentRow).toIntArray().forEach { rowIndex ->
            setRow(rowIndex, getRow(rowIndex) xorPlus getRow(currentRow))
        }
        currentRow++
    }
}

fun Matrix.refFindPivot(columnIndex: Int, row: Int) = when (row) {
    rowCount -> -1
    else -> {
        val pivot = getColumn(columnIndex)
            .slice(row until rowCount)
            .indexOfFirst { it == 1 }
        if (pivot == -1) pivot else pivot + row
    }
}


