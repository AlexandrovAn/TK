package utils

import Matrix


fun List<List<Int>>.toArrays() = (0 until size).map { this[it].toIntArray() }

fun List<List<Int>>.leadColumnsIndex(): List<Int> = map { row -> row.indexOfFirst { it == 1 } }.filterNot { it == -1 }

fun List<List<Int>>.filterLeadColumns(lead: List<Int>) =
    map { row -> row.filterIndexed { index, _ -> !lead.contains(index) } }