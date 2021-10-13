import utils.*

fun Matrix.ref(): Matrix {
    val result = Matrix(this)
    var row = 0

    (0 until colCount).forEach { i ->

        val index = result.findColumnIndexUnderPosition(i, row)
        if (index >= row) {
            if (index != row) result.swapRows(row, index)
            result.refDeleteOnes(i, row)
            row++
        }
    }
    return result
}

fun Matrix.findColumnIndexUnderPosition(columnIndex: Int, row: Int): Int {

    if (row == rowCount) return -1

    return when (val firstInColumn = this.getColumn(columnIndex).slice(row until rowCount).indexOfFirst { it == 1 }) {

        -1 -> firstInColumn
        else -> firstInColumn + row
    }
}

fun Matrix.refDeleteOnes(i: Int, index: Int) =
    columnFilter(
        column = i,
        underRowIndex = index
    ).toIntArray().forEach { rowIndex ->
        this.setRow(rowIndex, this.getRow(rowIndex) xorPlus this.getRow(index))
    }


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

