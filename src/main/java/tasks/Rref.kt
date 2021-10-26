import utils.*

fun Matrix.rref() = ref()
    .rowZeroFilter()
    .processRref()

fun Matrix.processRref() = Matrix(this).also { result ->
    (0 until result.rowCount).forEach { index ->
        result.deleteOnesAbovePivot(result.getRow(index).pivotIndex(), index)
    }
}

fun Matrix.deleteOnesAbovePivot(i: Int, index: Int) {
    columnFilter(i, aboveRowIndex = index).forEach { rowIndex ->
        setRow(rowIndex, getRow(rowIndex) xorPlus getRow(index))
    }
}




