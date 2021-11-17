package utils

import Matrix

fun identityMatrix(size: Int): List<List<Int>> {
    val matrix = Matrix(size, size)
    (0 until size).forEach { i ->
        val intArray = IntArray(size)
        intArray[i] = 1
        matrix.setRow(i, intArray)
    }
    return matrix.toListOfList()
}

infix fun Matrix.equalAsMultiplicity(other: Matrix): Boolean {
    val firstSet = toListOfList()
    val secondSet = other.toListOfList()
    return firstSet.containsAll(secondSet) && secondSet.containsAll(firstSet)
}

infix fun Int.multiply(matrix: Matrix) = matrix.toListOfList().map { row -> row.map { it * this } }.toMatrix()