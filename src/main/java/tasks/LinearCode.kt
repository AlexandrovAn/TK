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
        n = newMatrix.colCount
        k = newMatrix.rowCount
        result = newMatrix.toListOfList().createVerifyMatrix(n - k)
    }

    override fun toString() = result.toString()
}

fun List<List<Int>>.createVerifyMatrix(sizeOfIdentityMatrix: Int): Matrix {
    val leadColumns = leadColumnsIndex()
    val matrixX = filterColumns(leadColumns)
    val matrixI = identityMatrix(sizeOfIdentityMatrix).toMutableList()
    leadColumns.forEachIndexed { index, i -> matrixI.add(i, matrixX[index].toList()) }
    return matrixI.toArrays().toMatrix()
}