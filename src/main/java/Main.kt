import tasks.LinearCode
import utils.*

fun main() {

//    println("*********1.1********")
//    println(MatrixDictionary.refTestMatrix.toString())
//    println(MatrixDictionary.refTestMatrix.ref().toString())
//
//    println("*********1.2********")
//    println(MatrixDictionary.rrefTestMatrix.toString())
//    println(MatrixDictionary.rrefTestMatrix.rref().toString())
//
//    println("*********1.3********")
//    println(MatrixDictionary.linearCodeMatrix.toString())
//    println(LinearCode(MatrixDictionary.linearCodeMatrix).toString())
//
//    println("*********1.4********")
//    val firstCalculate = first(MatrixDictionary.codeWordsMatrix)
//    val secondCalculate = second(MatrixDictionary.codeWordsMatrix)
//    println(firstCalculate)
//    println(secondCalculate)
//
//    println("Равенство первого результата второму: ${firstCalculate equalAsMultiplicity  secondCalculate}")
//    println("Умножение для первого метода дает нулевые вектора: ${(firstCalculate multiply MatrixDictionary.arrayH).sum() == 0}")
//    println("Умножение для второго метода дает нулевые вектора: ${(secondCalculate multiply MatrixDictionary.arrayH).sum() == 0}")

//    println("*********1.5********")
//    val verificationMatrix = LinearCode(MatrixDictionary.linearCodeMatrix).result
//    val resultOfPreviousTask = first(MatrixDictionary.linearCodeMatrix)
//    println(verificationMatrix)
//    println(resultOfPreviousTask)
//    val d = resultOfPreviousTask.codeDistance()
//    val t = d - 1
//    println("d = $d t = $t")
//    val error1 = resultOfPreviousTask.generateError(4)
//    val error2 = intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 0, 0)
//    println("Ошибка: ${error1.toList()}")
//    resultOfPreviousTask.addError(error1)
//    println("Матрица с внедренной ошибкой:\n$resultOfPreviousTask")
//    resultOfPreviousTask.verifyErroredMatrix(verificationMatrix)

    println("*********ЛР 2 Часть 1********")
    println("*********2.1********")
    val G = MatrixDictionary.arrayG
    println(G)
    println("*********2.2/2.3********")
    val verifyMatrix = LinearCode(MatrixDictionary.arrayG)
    println(verifyMatrix)
    println("*********2.4********")
    val error1 = MatrixDictionary.arrayG.generateError(1)
    println("сформированная ошибка х1")
    println(error1.toList())
    println("внедряем ошибку")
    var testRow = G.getRow(0) xorPlus error1
    println(testRow.toList())
    println("вычисляем синдром")
    var syndrome = testRow multiply verifyMatrix.result
    println(syndrome.toList())
    println("находим где ошибка")
    println(verifyMatrix.result.findSameRow(syndrome) + 1)
    println("исправляем ошибку")
    testRow.correctError(verifyMatrix.result.findSameRow(syndrome))
    println(testRow.toList())
    val error2 = MatrixDictionary.arrayG.generateError(2)
    println("сформированная ошибка х2")
    println(error2.toList())
    println("внедряем ошибку х2")
    testRow = G.getRow(0) xorPlus error1
    println(testRow.toList())
    println((testRow multiply verifyMatrix.result).toList())
    val allXorCombinations = verifyMatrix.result.generateAllXorCombinations()
    println("таблица синдромов для ошибки х2")
    println(allXorCombinations.print())
    syndrome = testRow multiply verifyMatrix.result
    println("места возможной ошибки")
    println(allXorCombinations.findSameRow(syndrome))
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

    val arrayG: Matrix = Matrix(
        arrayOf(
            intArrayOf(1, 0, 0, 0, 1, 1, 1),
            intArrayOf(0, 1, 0, 0, 0, 1, 1),
            intArrayOf(0, 0, 1, 0, 1, 1, 0),
            intArrayOf(0, 0, 0, 1, 1, 0, 1),
        )
    )
}

