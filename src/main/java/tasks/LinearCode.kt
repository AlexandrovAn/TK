package tasks

import Matrix
import rref
import utils.*

class LinearCode(matrix: Matrix) {
    val result: Matrix

    private val n: Int
    private val k: Int

    init {
        val newMatrix = matrix.rref().apply {
            n = colCount
            k = rowCount
        }.to2DList()
        result = newMatrix.linearCodeFunction(n-k)
    }

    override fun toString(): String {
        return result.toString()
    }
}

fun List<List<Int>>.linearCodeFunction(sizeOfIdentityMatrix: Int): Matrix {
    val leadColumns = leadColumnsIndex()
    val matrixX = filterLeadColumns(leadColumns)
    val matrixI = identityMatrix(sizeOfIdentityMatrix).toMutableList()
    leadColumns.forEachIndexed { index, i ->
        matrixI.add(i, matrixX[index].toList())
    }
    return matrixI.toArrays().toMatrix()
}