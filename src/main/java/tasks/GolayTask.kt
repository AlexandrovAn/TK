package tasks

import Matrix
import utils.*

private val B = listOf(
    listOf(1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1),
    listOf(1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1),
    listOf(0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1),
    listOf(1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1),
    listOf(1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1),
    listOf(1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1),
    listOf(0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1),
    listOf(0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1),
    listOf(0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1),
    listOf(1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1),
    listOf(0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1),
    listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0)
)

fun generateGolayG() = identityMatrix(12).zip(B).map { (I, B) -> I + B }.toMatrix()

fun generateGolayH(): Matrix = (identityMatrix(12) + B).toMatrix()

fun List<List<Int>>.findMatchRowB(syndrome: IntArray): List<Int> {
    this.forEach { row -> if ((syndrome xorPlus row.toIntArray()).sum() <= 2) return row }
    return emptyList()
}

fun golayDecoding(errorX: Int): Any {
    println("DECODING FOR X$errorX")
    val H = generateGolayH()
    val G = generateGolayG()
    val error = G.generateError(errorX)
    println("Error for x$errorX = ${error.toList()}")
    val syndrome = error multiply H
    println("Start syndrome for x$errorX = ${syndrome.toList()}")
    when {
        syndrome.sum() <= 3 -> return syndrome.toList() + List(24 - syndrome.size) { 0 }
        B.findMatchRowB(syndrome).isNotEmpty() -> return (syndrome xorPlus B.findMatchRowB(syndrome).toIntArray())
            .toMutableList().let {
                it + MutableList(24 - it.size) { 0 }.apply { this[B.indexOf(B.findMatchRowB(syndrome))] = 1 }
                    .toList()
            }
        else -> {
            val newSyndrome = syndrome multiply B.toMatrix()
            println("New syndrome for x$errorX = ${newSyndrome.toList()}")
            return when {
                newSyndrome.sum() <= 3 -> List(24 - syndrome.size) { 0 } + newSyndrome.toList()
                B.findMatchRowB(newSyndrome).isNotEmpty() -> {
                    val right = (newSyndrome xorPlus B.findMatchRowB(newSyndrome).toIntArray()).toMutableList()
                    MutableList(24 - right.size) { 0 }.apply {
                        this[B.indexOf(B.findMatchRowB(newSyndrome))] = 1
                    }.toList() + right
                }
                else -> "Not found"
            }
        }
    }
}