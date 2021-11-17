package tasks

import Matrix
import utils.*
import kotlin.math.abs
import kotlin.math.pow

private val G11 = listOf(
    listOf(1, 1),
    listOf(0, 1)
)

private val kronekerH: Matrix = Matrix(
    arrayOf(
        intArrayOf(1, 1),
        intArrayOf(1, -1)
    )
)

fun generateReedMillerG(r: Int, m: Int): Matrix {
    if (r == 1 && m == 1) return G11.toMatrix()
    val left = generateReedMillerG(r, m - 1).toListOfList().toMutableList()
        .apply { add(List<Int>((2.0.pow(m) / 2).toInt()) { 0 }) }.toList()
    val right = generateReedMillerG(r, m - 1).toListOfList().toMutableList()
        .apply { add(List((2.0.pow(m) / 2).toInt()) { 1 }) }.toList()
    return left.zip(right).map { (l, r) -> l + r }.toMatrix()
}

infix fun Matrix.multiplyKroneker(other: Matrix): Matrix {
    val firstMatrix = this.toListOfList()
    var currMatrix = emptyList<List<Int>>()
    firstMatrix.forEach { row ->
        var currRow = emptyList<List<Int>>()
        row.forEach { element ->
            val res = (element multiply other).toListOfList()
            currRow = if (currRow.isEmpty()) res else currRow.zip(res).map { (l, r) -> l + r }
        }
        currMatrix = if (currMatrix.isEmpty()) currRow else currMatrix + currRow
    }
    return currMatrix.toMatrix()
}

fun reedMillerDecoding(r: Int, m: Int) {
    val G = generateReedMillerG(r, m)
    println("Reed-Miller G for ($r,$m):\n${G}")
    if (m == 3) {
        var x1ErrorG = G.generateError(1).toList()
        var x2ErrorG = G.generateError(2).toList()
        println("Error for x1 = $x1ErrorG")
        println("Error for x2 = $x2ErrorG")
        x1ErrorG = x1ErrorG.withNegative()
        x2ErrorG = x2ErrorG.withNegative()
        println("Error for x1 with negative = $x1ErrorG")
        println("Error for x2 with negative = $x2ErrorG")
        x1ErrorG = x1ErrorG.calculateV(m, m).toList()
        x2ErrorG = x2ErrorG.calculateV(m, m).toList()
        println("V for x1 = $x1ErrorG")
        println("V for x2 = $x2ErrorG")
        println("msg for x1 = ${x1ErrorG.findReedMillerMsg(m)}")
        println("msg for x2 = ${x2ErrorG.findReedMillerMsg(m)}")
    }
    if (m == 4){
        var x1ErrorG = G.generateError(1).toList()
        var x2ErrorG = G.generateError(2).toList()
        var x3ErrorG = G.generateError(3).toList()
        var x4ErrorG = G.generateError(4).toList()
        println("Error for x1 = $x1ErrorG")
        println("Error for x2 = $x2ErrorG")
        println("Error for x3 = $x3ErrorG")
        println("Error for x4 = $x4ErrorG")
        x1ErrorG = x1ErrorG.withNegative()
        x2ErrorG = x2ErrorG.withNegative()
        x3ErrorG = x3ErrorG.withNegative()
        x4ErrorG = x4ErrorG.withNegative()
        println("Error for x1 with negative = $x1ErrorG")
        println("Error for x2 with negative = $x2ErrorG")
        println("Error for x3 with negative = $x3ErrorG")
        println("Error for x4 with negative = $x4ErrorG")
        x1ErrorG = x1ErrorG.calculateV(m, m).toList()
        x2ErrorG = x2ErrorG.calculateV(m, m).toList()
        x3ErrorG = x3ErrorG.calculateV(m, m).toList()
        x4ErrorG = x4ErrorG.calculateV(m, m).toList()
        println("V for x1 = $x1ErrorG")
        println("V for x2 = $x2ErrorG")
        println("V for x3 = $x3ErrorG")
        println("V for x4 = $x4ErrorG")
        println("msg for x1 = ${x1ErrorG.findReedMillerMsg(m)}")
        println("msg for x2 = ${x2ErrorG.findReedMillerMsg(m)}")
        println("msg for x3 = ${x3ErrorG.findReedMillerMsg(m)}")
        println("msg for x4 = ${x4ErrorG.findReedMillerMsg(m)}")
    }
}

fun Int.toBinary(len: Int): String {
    return String.format("%" + len + "s", this.toString(2)).replace(" ".toRegex(), "0")
}

fun List<Int>.findReedMillerMsg(m: Int): String {
    val list = this.map { abs(it) }
    val index = list.indexOf(list.maxOrNull())
    val maxIndex =
        index.toBinary(m).let { if (this[index] > 0) it + '1' else it + '0' }.toString().reversed()
    return maxIndex
}

fun List<Int>.calculateV(arg: Int, m: Int): IntArray {
    if (arg == 1) return toIntArray() multiply generateHWithKroneker(m, arg)
    return calculateV(arg - 1, m) multiply generateHWithKroneker(m, arg)
}

fun List<Int>.withNegative() = toMutableList().map { if (it == 0) it - 1 else it }

fun generateHWithKroneker(m: Int, i: Int) =
    (identityMatrix((2.0.pow(m - i)).toInt()).toMatrix() multiplyKroneker kronekerH) multiplyKroneker identityMatrix(
        (2.0.pow(
            i - 1
        )).toInt()
    ).toMatrix()
