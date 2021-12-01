package tasks

import Matrix
import utils.generateError
import utils.toListOfList
import utils.toMatrix
import utils.xorPlus


fun generatePolynomG(polynomDegrees: List<Int>, n: Int, k: Int): Matrix {
    val prevRow = MutableList<Int>(n) { 0 }
    polynomDegrees.forEach { degree -> prevRow[degree] = 1 }
    val matrix = emptyList<List<Int>>().toMutableList()
    (0 until k).forEach { index ->
        matrix += prevRow.toList()
        prevRow.removeLast()
        prevRow.add(0, 0)
    }
    return matrix.toMatrix()
}

fun multiplyPolynomsWithDegrees(first: List<Int>, second: List<Int>): List<Int> {
    val result = emptyList<Int>().toMutableList()
    first.forEach { firstD ->
        second.forEach { secodD ->
            result += firstD + secodD
        }
    }
    return result.filterResultPolynom()
}

fun MutableList<Int>.filterResultPolynom(): List<Int> {
    val result = emptyList<Int>().toMutableList()
    sort()
    forEachIndexed { _, element ->
        if (result.isEmpty() || result.last() != element) result += element else result.removeLast()
    }
    return result
}

infix fun List<Int>.polynomsMod(divider: List<Int>): Pair<List<Int>, List<Int>> {
    var mod = this
    var dividendSenDegree = mod.maxOrNull() ?: 0
    val dividerSenDegree = divider.maxOrNull() ?: 0
    val diffList = emptyList<Int>().toMutableList()
    while (dividendSenDegree >= dividerSenDegree) {
        val diffSenDegrees = dividendSenDegree - dividerSenDegree
        diffList += diffSenDegrees
        val step = divider.map { it + diffSenDegrees }.toMutableList().filterResultPolynom()
        mod = (mod + step).sorted().toMutableList().filterResultPolynom()
        dividendSenDegree = mod.maxOrNull() ?: 0
    }
    return Pair(diffList, mod)

}

fun decodingFun(g: List<Int>, wVector: List<Int>, n: Int): List<Int> {
    val w = wVector.convertToPolynom().also { println("w = $it") }
    val s = (w polynomsMod g).second
    var polynom = s
    var leftPart = s
    var condition = true
    var i = 0
    while (condition) {
        leftPart = leftPart.map { it + 1 }
        condition = (leftPart polynomsMod g).second.size > 1
        polynom = (leftPart polynomsMod g).second
        println("i = $i S$i(x) = $polynom")
        i++
    }
    val e = polynom.map { it + (n - i) }.also { println("e = $it") }
    val result = (w + e).toMutableList().filterResultPolynom().also { println("w + e = $it") }
    return result
}

fun decodingFunForPack(g: List<Int>, wVector: List<Int>, n: Int, m: Int): List<Int> {
    val w = wVector.convertToPolynom().also { println("w = $it") }
    val errorPack = generateErrorPack(m, n)
    println("Error pack:\n${errorPack.toMatrix()}")
    val s = (w polynomsMod g).second
    var polynom = s
    var leftPart = s
    var condition = true
    var i = 0
    while (condition) {
        condition = (leftPart polynomsMod g).second.size > 1
        polynom = (leftPart polynomsMod g).second
        if (errorPack.contains(polynom.convertToVector(n))) condition = false
        println("i = $i S$i(x) = $polynom")
        i++
        leftPart = leftPart.map { it + 1 }
    }
    val e = polynom.map { it + (n - i + 1) }.also { println("e = $it") }
    val result = (w + e).toMutableList().filterResultPolynom().also { println("w + e = $it") }
    return result
}

fun List<Int>.convertToVector(resultSize: Int) = List<Int>(resultSize) { 0 }.toMutableList().also { result ->
    forEach { index -> result[index] = 1 }
}

fun List<Int>.convertToPolynom() = emptyList<Int>().toMutableList().also { result ->
    forEachIndexed { index, item ->
        if (item == 1) result += index
    }
}.toList()

fun generateErrorPack(m: Int, n: Int) =
    allCombinations(m).toListOfList().filter { it.first() == 1 }.map { it + List(n - m) { 0 } }

fun generateLab6Error(t: Int, n: Int, g: List<Int>, G: Matrix) =
    multiplyPolynomsWithDegrees(listOf(0, 3), g).convertToVector(n).also { println("v = $it") } xorPlus G.generateError(
        t
    ).toList().also { println("Generated error = $it") }

