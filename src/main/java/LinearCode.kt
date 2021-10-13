import utils.setRow

class LinearCode(private val matrix: Matrix) {
    private val result: Matrix

    val n: Int
    val k: Int

    init {
        val newMatrix = matrix.rref().apply {
            n = colCount
            k = rowCount
        }.to2DList()
        val leadColumns = newMatrix.leadColumnsIndex()
        val X = newMatrix.filterLeadColumns(leadColumns)
        val I = identityMatrix(n - k).toMutableList()
        leadColumns.forEachIndexed { index, i ->
            I.add(i, X[index].toList())
        }
        result = I.toArrays().toMatrix()
    }

    override fun toString(): String {
        return result.toString()
    }

}

fun List<List<Int>>.toArrays() = (0 until size).map { this[it].toIntArray() }

fun List<IntArray>.toMatrix(): Matrix {
    val matrix = Matrix(size, this[0].size)
    (0 until size).forEach { i ->
        matrix.setRow(i, this[i])
    }
    return matrix
}

fun Matrix.to2DList() = (0 until rowCount).map { getThisRow(it).toList() }
fun Array<Array<Int>>.to2DList() = (0 until size).map { this[it].toList() }

fun identityMatrix(size: Int): List<List<Int>> {
    val matrix = Matrix(size, size)
    (0 until size).forEach { i ->
        val intArray = IntArray(size)
        intArray[i] = 1
        matrix.setRow(i, intArray)
    }
    return matrix.to2DList()
}

fun List<List<Int>>.leadColumnsIndex(): List<Int> = map { row -> row.indexOfFirst { it == 1 } }.filterNot { it == -1 }

fun List<List<Int>>.filterLeadColumns(lead: List<Int>) =
    map { row -> row.filterIndexed { index, _ -> !lead.contains(index) } }
