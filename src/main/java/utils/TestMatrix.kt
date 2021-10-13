package utils

import Matrix

object TestMatrix {
    val refTestMatrix: Matrix = Matrix(arrayOf(
        intArrayOf(1, 0, 1, 1, 0, 0, 0, 1, 0, 0),
        intArrayOf(0, 0, 0, 1, 1, 1, 0, 1, 0, 1),
        intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 0, 0),
        intArrayOf(1, 0, 1, 0, 1, 1, 1, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 1, 1),
    ))

    val deleteZeroRowsTest: Matrix = Matrix(arrayOf(
        intArrayOf(1, 0, 1, 1, 0, 0, 0, 1, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 0, 0),
        intArrayOf(1, 0, 1, 0, 1, 1, 1, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    ))
}