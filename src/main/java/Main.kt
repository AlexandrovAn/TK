import tasks.LinearCode
import utils.*

fun main() {

    println("*********1.1********")
    println(MatrixDictionary.refTestMatrix.toString())
    println(MatrixDictionary.refTestMatrix.ref().toString())

    println("*********1.2********")
    println(MatrixDictionary.rrefTestMatrix.toString())
    println(MatrixDictionary.rrefTestMatrix.rref().toString())

    println("*********1.3********")
    println(MatrixDictionary.linearCodeMatrix.toString())
    println(LinearCode(MatrixDictionary.linearCodeMatrix).toString())

}

object MatrixDictionary {
    val refTestMatrix: Matrix = Matrix(
        arrayOf(
            intArrayOf(1, 0, 1, 1, 0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 1, 1, 1, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 0, 0),
            intArrayOf(1, 0, 1, 0, 1, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 1, 1),
        )
    )

    val rrefTestMatrix: Matrix = Matrix(
        arrayOf(
            intArrayOf(1, 0, 1, 1, 0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 1, 1, 1, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 0, 0),
            intArrayOf(1, 0, 1, 0, 1, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 1, 1),
        )
    )

    val linearCodeMatrix: Matrix = Matrix(
        arrayOf(
            intArrayOf(1, 0, 1, 1, 0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 1, 1, 1, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 0, 0),
            intArrayOf(1, 0, 1, 0, 1, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 1, 1),
        )
    )

    val deleteZeroRowsTest: Matrix = Matrix(
        arrayOf(
            intArrayOf(1, 0, 1, 1, 0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 0, 0),
            intArrayOf(1, 0, 1, 0, 1, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        )
    )
}

