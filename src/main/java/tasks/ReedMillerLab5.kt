package tasks

import Matrix
import utils.*
import kotlin.math.pow

fun standartOrder(m: Int) = listOf(List<Int>(m) { 0 }) + allCombinations(m).toListOfList().map { it.reversed() }

fun generateI(m: Int): List<Int> = emptyList<Int>().toMutableList().also { result ->
    (0 until m).forEach { index -> result += index }
}

fun generateV(I: List<Int>, m: Int): List<Int> = emptyList<Int>().toMutableList().also { result ->
    standartOrder(m).forEach { xVector -> result += F(I, xVector) }
}

fun F(I: List<Int>, xVector: List<Int>): Int {
    I.forEach { index -> if (xVector[index] == 1) return 0 }
    return 1
}

fun generateICombinations(I: List<Int>, iteration: Int): List<List<Int>> {
    if (iteration == 0) return emptyList()
    val result = emptyList<List<Int>>().toMutableList()
    I.forEach { index ->
        if (index + iteration - 1 <= I.size) {
            val matchBlock = I.subList(index, index + iteration - 1).toMutableList()
            I.forEachIndexed { _, item ->
                if (!matchBlock.contains(item)) {
                    val step = matchBlock + item
                    result += step
                }
            }
        }
    }
    return result.map { it.sorted() }.distinct()
}

fun generateNewBlock(I: List<Int>, iteration: Int, m: Int) = emptyList<List<Int>>().toMutableList().also { result ->
    val ICombinations = generateICombinations(I, iteration)
    ICombinations.forEach { combination ->
        result += generateV(combination, m)
    }
}.sortedBy { row -> row.indexOfLast { item -> item == 1 } }

fun generateGLab5(r: Int, m: Int): Matrix {
    val G = emptyList<List<Int>>().toMutableList()
    val I = generateI(m)
    G += List<Int>(2.0.pow(m).toInt()) { 1 }
    (1 until r + 1).forEach { iteration ->
        G += generateNewBlock(I, iteration, m)
    }
    return G.toMatrix()
}

fun reedMillerDecodingLab5(r: Int, m: Int): Unit? {
    val G = generateGLab5(r, m).toListOfList()
    println("Matrix G:\n${G.toMatrix()}")
    val I = generateI(m)
    var i = r
//    listOf(0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0) - слово из примера
    val error = G.toMatrix().generateError(1)
    println("Error = ${error.toList()}")
    var w = (error xorPlus (intArrayOf(0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0) multiply G.toMatrix())).toList()
    println("w = $w")
    val msList = emptyList<Int>().toMutableList()
    while (i >= 0) {
        val JsList = emptyList<List<Int>>().toMutableList()
        val ICombinations = generateICombinations(I, i)
        if (ICombinations.isEmpty()) {
            val step = atomIteration(emptyList(), I, m, w)
            if (step == null) {
                println("Cant decoding this word, repeat send")
                return null
            }
            msList += step
        }
        ICombinations.forEach { combination ->
            val step = atomIteration(combination, I, m, w)
            if (step == null) {
                println("Cant decoding this word, repeat send")
                return null
            }
            msList += step
            if (step == 1) JsList += combination
            println("m for $combination =  $step")
        }
        if (JsList.isNotEmpty()) JsList.forEach { combination -> w = w xorPlus generateV(combination, m) }
        i--
    }
    println("Received message * G = ${(msList.toIntArray() multiply G.toMatrix()).toList()}")
    println("Received message = $msList")
    return Unit
}

fun atomIteration(J: List<Int>, I: List<Int>, m: Int, w: List<Int>): Int? {
    val Jc = I.generateJc(J)
    val Hj = standartOrder(m).filter { F(J, it) == 1 }
    val vectors = emptyList<List<Int>>().toMutableList()
    val v = generateV(Jc, m)
    Hj.forEach { item ->
        val t = item.tCalculator()
        val step = v.subList(0, v.size - t)
        vectors += List<Int>(t) { 0 } + step
    }
    return w.mCalculator(vectors)
}

fun List<Int>.mCalculator(vectors: List<List<Int>>): Int? {
    val multiply = vectors.map { this multiplyForModule2 it }
    return when {
        multiply.count { it == 1 } > multiply.count { it == 0 } -> 1
        multiply.count { it == 1 } < multiply.count { it == 0 } -> 0
        else -> null
    }
}

infix fun List<Int>.multiplyForModule2(v: List<Int>): Int {
    val result = emptyList<Int>().toMutableList()
    forEachIndexed { index, _ ->
        result += this[index] * v[index]
    }
    var sum = 0
    result.forEach { item ->
        sum = item xor sum
    }
    return sum
}


fun List<Int>.tCalculator(): Int {
    var sum = 0
    forEachIndexed { index, item -> if (item == 1) sum += 2.0.pow(index).toInt() }
    return sum
}

fun List<Int>.generateJc(J: List<Int>) = filterNot { Ielement -> J.contains(Ielement) }


