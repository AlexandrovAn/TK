import tasks.*
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

//    println("*********ЛР 2 Часть 1********")
//    firstPhase(MatrixDictionary.arrayG1)
//    secondPhase(MatrixDictionary.arrayG1)
//    firstPhase(MatrixDictionary.arrayG2)
//    secondPhase(MatrixDictionary.arrayG2)
//    thirdPhase(MatrixDictionary.arrayG2)

//    println("*********ЛР 3********")
//    hamingTask(2)
//    hamingTask(3)
//    hamingTask(4)
//    extendedHamingTask(2)
//    extendedHamingTask(3)
//    extendedHamingTask(4)

    println("*********ЛР 4********")
    golayTask()
//    reedMillerTask()

}

fun reedMillerTask() {
    println("*********REED-MILLER TASK********")
    println("*********(1,3)********")
    reedMillerDecoding(1, 3)
    println("*********(1,4)********")
    reedMillerDecoding(1, 4)
}

fun golayTask() {
    println("*********GOLAY TASK********")
    val G = generateGolayG()
    val H = generateGolayH()
    println("Golay G:\n${G}")
    println("Golay H:\n${H}")
    println("U for x1 = ${golayDecoding(1)}")
    println("U for x2 = ${golayDecoding(2)}")
    println("U for x3 = ${golayDecoding(3)}")
    println("U for x4 = ${golayDecoding(4)}")
}

fun extendedHamingTask(size: Int) {
    println("Extended for size $size")
    val H = generateExtendedHForHamingCode(size)
    val G = generateExtendedGForHamingCode(size)
    val x1ErrorG = G.generateError(1)
    val x2ErrorG = G.generateError(2)
    val x3ErrorG = G.generateError(3)
    val x4ErrorG = G.generateError(4)
    println(H)
    println(G)
    val syndromeX1 = x1ErrorG multiply H
    val syndromeX2 = x2ErrorG multiply H
    val syndromeX3 = x3ErrorG multiply H
    val syndromeX4 = x4ErrorG multiply H
    println("Syndrome for x1 : ${syndromeX1.toList()}")
    println("Syndrome for x2 : ${syndromeX2.toList()}")
    println("Syndrome for x3 : ${syndromeX3.toList()}")
    println("Syndrome for x4 : ${syndromeX4.toList()}")
    println("Find x1 error in H")
    println(H.findSameRow(syndromeX1))
    println("Find x2 error in H")
    println(H.findSameRow(syndromeX2))
    println("Find x3 error in H")
    println(H.findSameRow(syndromeX3))
    println("Find x4 error in H")
    println(H.findSameRow(syndromeX4))
}

fun hamingTask(size: Int) {
    println("Default for size $size")
    val H = generateHForHamingCode(size)
    val G = generateGForHamingCode(size)
    val x1ErrorG = G.generateError(1)
    val x2ErrorG = G.generateError(2)
    val x3ErrorG = G.generateError(3)
    println(H)
    println(G)
    val syndromeX1 = x1ErrorG multiply H
    val syndromeX2 = x2ErrorG multiply H
    val syndromeX3 = x3ErrorG multiply H
    println("Syndrome for x1 : ${syndromeX1.toList()}")
    println("Syndrome for x2 : ${syndromeX2.toList()}")
    println("Syndrome for x3 : ${syndromeX3.toList()}")
    println("Find x1 error in H")
    println(H.findSameRow(syndromeX1))
    println("Find x2 error in H")
    println(H.findSameRow(syndromeX2))
    println("Find x3 error in H")
    println(H.findSameRow(syndromeX3))
}

fun firstPhase(G: Matrix) {
    println("*********2.1********")
    println(G)
    println("*********2.2/2.3********")
    val verifyMatrix = LinearCode(G)
    println(verifyMatrix)
    println("*********2.4********")
    val error1 = G.generateError(1)
    println("сформированная ошибка х1")
    println(error1.toList())
    println("внедряем ошибку")
    val testRow = G.getRow(0) xorPlus error1
    println(testRow.toList())
    println("вычисляем синдром")
    val syndrome = testRow multiply verifyMatrix.result
    println(syndrome.toList())
    println("находим где ошибка")
    println(verifyMatrix.result.findSameRow(syndrome))
    println("исправляем ошибку")
    testRow.correctError(verifyMatrix.result.findSameRow(syndrome))
    println(testRow.toList())
}

fun secondPhase(G: Matrix) {
    //Проверка валидности второй матрицы
//    val second = second(G)
//    println(
//        allCombinations(G.rowCount).to2DList().zip(second.to2DList())
//            .filter { (_, item) -> item.sum() < 5 }
//            .joinToString(separator = "\n")
//    )
    val error2 = G.generateError(2)
    val verifyMatrix = LinearCode(G)
    println("сформированная ошибка х2")
    println(error2.toList())
    println("внедряем ошибку х2")
    val testRow = G.getRow(0) xorPlus error2
    println(testRow.toList())
    val allXorCombinations = verifyMatrix.result.generateAllXorCombinations()
    println("таблица синдромов для ошибки х2")
    allXorCombinations.print()
    val syndrome = testRow multiply verifyMatrix.result
    println("вычисляем синдром")
    println(syndrome.toList())
    println("места ошибки")
    val errorPlace = allXorCombinations.findSameRow(syndrome)
    println(errorPlace)
    if (errorPlace.size == 1) {
        testRow.correctError(errorPlace.first().first)
        testRow.correctError(errorPlace.first().second)
        println("исправили ошибку")
        println(testRow.toList())
    }
}

fun thirdPhase(G: Matrix) {
    val verifyMatrix = LinearCode(G)
    val error3 = G.generateError(3)
    println("ошибка х3 ${error3.toList()}")
    println("строка 0: ${G.getRow(0).toList()}")
    val testRow = G.getRow(0) xorPlus error3
    println("внедряем ошибку х3")
    println(testRow.toList())
    val syndrome = testRow multiply verifyMatrix.result
    println("синдром ${syndrome.toList()}")
    val allXorCombinations = verifyMatrix.result.generateAllXorCombinations()
    println("места ошибки")
    println(allXorCombinations.findSameRow(syndrome))
    println(verifyMatrix.result.findSameRow(syndrome))
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

    val arrayG1: Matrix = Matrix(
        arrayOf(
            intArrayOf(1, 0, 0, 0, 1, 1, 1),
            intArrayOf(0, 1, 0, 0, 0, 1, 1),
            intArrayOf(0, 0, 1, 0, 1, 1, 0),
            intArrayOf(0, 0, 0, 1, 1, 0, 1),
        )
    )

    val arrayG2: Matrix = Matrix(
        arrayOf(
            intArrayOf(1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1),
            intArrayOf(0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0),
            intArrayOf(0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0),
            intArrayOf(0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0),
        )
    )

    val testKroneker1: Matrix = Matrix(
        arrayOf(
            intArrayOf(1, 1),
            intArrayOf(1, -1)
        )
    )
}

