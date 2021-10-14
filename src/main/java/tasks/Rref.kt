import utils.*

fun Matrix.rref(): Matrix {
    val result = Matrix(this).ref().rowZeroFilter()
    (0 until result.rowCount).forEach { i ->
        val row = result.getRow(i)
        val pivotIndex = row.pivotIndex()
        result.deleteOnesAbovePivot(pivotIndex, i)
    }
    return result
}

fun Matrix.deleteOnesAbovePivot(i: Int, index: Int) {
    columnFilter(i, aboveRowIndex = index).forEach { rowIndex ->
        setRow(rowIndex, getRow(rowIndex) xorPlus getRow(index))
    }
}




