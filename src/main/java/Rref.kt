import utils.setRow
import utils.xorPlus

fun Matrix.rref(): Matrix {
    val result = Matrix(this).ref().rowZeroFilter()
    (0 until result.rowCount).forEach { i ->
        val row = result.getRow(i)
        val pivotIndex = row.pivotIndex()
        result.deleteOnesAbovePivot(pivotIndex, i)
    }
    return result
}

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

fun IntArray.pivotIndex() = toList().indexOfFirst { it == 1 }

fun Matrix.deleteOnesAbovePivot(i: Int, index: Int) {
    columnFilter(i, aboveRowIndex = index).forEach { rowIndex ->
        setRow(rowIndex, getRow(rowIndex) xorPlus getRow(index))
    }
}




