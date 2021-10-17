import tasks.LinearCode
import tasks.allCombinations
import tasks.first
import tasks.second
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

    println("*********1.4********")
    val firstCalculate = first(MatrixDictionary.codeWordsMatrix)
    val secondCalculate = second(MatrixDictionary.codeWordsMatrix)
    println(firstCalculate)
    println(secondCalculate)

    println("Равенство первого результата второму: ${firstCalculate equalAsMultiplicity  secondCalculate}")
    println("Умножение для первого метода дает нулевые вектора: ${(firstCalculate multiply MatrixDictionary.arrayH).sum() == 0}")
    println("Умножение для второго метода дает нулевые вектора: ${(secondCalculate multiply MatrixDictionary.arrayH).sum() == 0}")


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

    val equalsTest1: Matrix = Matrix(
        arrayOf(
            intArrayOf(1, 0, 1, 1, 0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 1, 1, 1, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 0, 0),
            intArrayOf(1, 0, 1, 0, 1, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 1, 1),
        )
    )

    val equalsTest2: Matrix = Matrix(
        arrayOf(
            intArrayOf(1, 0, 1, 1, 0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 0, 0),
            intArrayOf(1, 0, 1, 0, 1, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 1, 1),
            intArrayOf(0, 0, 0, 1, 1, 1, 0, 1, 0, 1),
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

    val codeWordsMatrix: Matrix = Matrix(
        arrayOf(
            intArrayOf(1, 0, 1, 1, 0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 1, 1, 1, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 1, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 1),
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

    val arrayH: Matrix = Matrix(
        arrayOf(
            intArrayOf(0, 1, 1, 1, 1),
            intArrayOf(1, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 1, 0),
            intArrayOf(0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 1),
            intArrayOf(0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 1),
        )
    )
}

