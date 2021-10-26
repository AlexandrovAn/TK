package tasks

import Matrix
import rref
import utils.*

class LinearCode(matrix: Matrix) {
    val result: Matrix

    private val n: Int
    private val k: Int

    init {
        val newMatrix = matrix.rref()
        n = newMatrix.rowCount
        k = newMatrix.colCount
        result = newMatrix.to2DList().createVerifyMatrix(n-k)
    }

    override fun toString(): String {
        return result.toString()
    }
}

fun List<List<Int>>.createVerifyMatrix(sizeOfIdentityMatrix: Int): Matrix {
    val leadColumns = leadColumnsIndex()
    val matrixX = filterLeadColumns(leadColumns)
    val matrixI = identityMatrix(sizeOfIdentityMatrix).toMutableList()
    leadColumns.forEachIndexed { index, i ->
        matrixI.add(i, matrixX[index].toList())
    }
    return matrixI.toArrays().toMatrix()
}