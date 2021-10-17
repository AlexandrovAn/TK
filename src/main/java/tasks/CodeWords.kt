package tasks

import Matrix
import utils.*
import kotlin.math.pow

fun first(matrix: Matrix): Matrix {

    val resultMatrix = matrix.to2DList().toMutableList()

    (resultMatrix.indices).forEach { i ->
        (i until resultMatrix.size).forEach { j ->
            val xor = resultMatrix[i] xorPlus resultMatrix[j]
            if (!resultMatrix.contains(xor)) resultMatrix.add(xor)
        }
    }

    return resultMatrix.toMatrix().rowZeroFilter()

}

fun second(matrix: Matrix) = allCombinations(matrix.rowCount) multiply matrix

fun allCombinations(length: Int): Matrix {
    val combinations = Matrix((2.0.pow(length) - 1).toInt(), length)

    (0 until combinations.rowCount).forEach { row ->
        val numericValue = Integer.toBinaryString(row + 1).toIntArray()
        val currRowValue = IntArray(length - numericValue.size) + numericValue
        combinations.setRow(row, currRowValue)
    }

    return combinations
}