package utils

fun Array<Array<IntArray>>.findSameRow(row: IntArray): ArrayList<Pair<Int, Int>> {
    val result = ArrayList<Pair<Int, Int>>()
    forEachIndexed { i, arrayOfIntArrays ->
        arrayOfIntArrays.forEachIndexed { j, item -> if (row.contentEquals(item) && (i < j)) result.add(i to j) }
    }
    return result
}

fun Array<Array<IntArray>>.to3DList() = (0 until size).map { this[it].toListOfList() }

fun Array<IntArray>.toListOfList() = (0 until size).map { this[it].toList() }

fun Array<Array<IntArray>>.print(): Unit = to3DList().forEach { println(it) }