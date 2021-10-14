package utils

infix fun IntArray.xorPlus(array: IntArray) = mapIndexed { index: Int, item: Int ->
    item xor array[index]
}.toIntArray()

fun IntArray.pivotIndex() = toList().indexOfFirst { it == 1 }