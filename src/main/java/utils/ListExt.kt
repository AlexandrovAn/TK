package utils

infix fun List<Int>.xorPlus(array: List<Int>) = mapIndexed { index: Int, item: Int ->
    item xor array[index]
}

fun String.toIntArray() = map(Character::getNumericValue).toIntArray()