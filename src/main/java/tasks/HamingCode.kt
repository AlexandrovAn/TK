package tasks

import Matrix
import utils.identityMatrix
import utils.toListOfList
import utils.toMatrix
import kotlin.math.pow

fun generateHForHamingCode(size: Int) = allCombinations(size).toListOfList()
    .filter { row -> row.sum() > 1 }
    .reversed()
    .let { filteredMatrix -> filteredMatrix + identityMatrix(size) }.toMatrix()

fun generateGForHamingCode(size: Int): Matrix {
    val actualSize = (2.0.pow(size) - 1).toInt()
    val right = allCombinations(size).toListOfList()
        .filter { row -> row.sum() > 1 }
        .reversed()
    val left = identityMatrix(actualSize - size)
    return left.zip(right).map { (l, r) -> l + r }.toMatrix()
}

fun generateExtendedHForHamingCode(size: Int) = (generateHForHamingCode(size).toListOfList()
    .toMutableList().apply { add(List<Int>(size) { 0 }) })
    .map { it + 1 }.toMatrix()

fun generateExtendedGForHamingCode(size: Int) = generateGForHamingCode(size).toListOfList()
    .map { it + if (it.sum() % 2 == 0) 0 else 1 }.toMatrix()

