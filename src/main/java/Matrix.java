import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class Matrix {
    private final int rowCount;
    private final int colCount;

    private int matrix[][];

    public Matrix(int rowCount, int colCount) {
        if ((rowCount <= 0) || (colCount <= 0))
            throw new IllegalArgumentException("Each dimension must be greater than zero");

        this.rowCount = rowCount;
        this.colCount = colCount;

        matrix = new int[rowCount][colCount];
    }

    public Matrix(Matrix other) {
        this.rowCount = other.rowCount;
        this.colCount = other.colCount;

        this.matrix = BasicOperations.clone2dArray(other.matrix);
    }

    public Matrix(int matrix[][]) {
        if ((matrix.length == 0) || (matrix[0].length == 0))
            throw new IllegalArgumentException("Each dimension must be greater than zero");

        this.rowCount = matrix.length;
        this.colCount = matrix[0].length;

        this.matrix = BasicOperations.clone2dArray(matrix);
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public void set(int i, int j, int value) {
        if ((i >= rowCount) || (j >= colCount))
            throw new IllegalArgumentException();

        matrix[i][j] = value;
    }

    public int get(int i, int j) {
        if ((i >= rowCount) || (j >= colCount))
            throw new IllegalArgumentException();

        return matrix[i][j];
    }

    public int[] getRow (int i) {
        int[] row = new int[colCount];
        for (int j = 0; j < colCount ; j++) {
            row[j] = get(i,j);
        }
        return row;
    }

    public int[] getColumn (int j) {
        int[] column = new int[rowCount];
        for (int i = 0; i < rowCount ; i++) {
            column[i] = get(i,j);
        }
        return column;
    }

    public Matrix add(Matrix other) {
        if ((this.colCount != other.colCount) || (this.rowCount != other.rowCount))
            throw new IllegalArgumentException("Matrices must have equal dimensions");

        return BasicOperations.operate(this, other,(a, b) -> a + b);
    }

    public Matrix subtract(Matrix other) {
        if ((this.colCount != other.colCount) || (this.rowCount != other.rowCount))
            throw new IllegalArgumentException("Matrices must have equal dimensions");

        return BasicOperations.operate(this, other, (a, b) -> a - b);
    }

    public Matrix multiply(Matrix other) {
        if ((this.colCount != other.rowCount) || (this.rowCount != other.colCount))
            throw new IllegalArgumentException("Multiplication condition not matched");

        return new Matrix(Arrays.stream(this.matrix).map(r ->
                IntStream.range(0, other.matrix[0].length)
                        .map(i ->
                                IntStream.range(0, other.matrix.length)
                                        .map(j -> r[j] * other.matrix[j][i]).sum())
                        .toArray())
                .toArray(int[][]::new));
    }

    public Matrix scale(int factor) {
        return new Matrix(Arrays.stream(matrix)
                .map(row -> Arrays.stream(row)
                        .map(x -> x * factor)
                        .toArray())
                .toArray(int[][]::new));
    }

    public Matrix transpose() {
        return new Matrix(IntStream.range(0, matrix[0].length)
                .mapToObj(r -> Arrays.stream(matrix)
                        .map(ints -> ints[r])
                        .toArray())
                .toArray(int[][]::new));
    }

    @Override
    public String toString() {
        return BasicOperations.matrixToString(matrix) + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Matrix))
            return false;

        Matrix matrix1 = (Matrix) o;
        return ((rowCount == matrix1.rowCount) &&
                (colCount == matrix1.colCount) &&
                Arrays.deepEquals(matrix, matrix1.matrix));
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(rowCount, colCount);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }

    private static class BasicOperations {

        private static Matrix operate(Matrix op1, Matrix op2, BiFunction<Integer, Integer, Integer> function) {
            Matrix result = new Matrix(op1);

            for (int i = 0; i < result.rowCount; i++) {
                for (int j = 0; j < result.colCount; j++)
                    result.matrix[i][j] = function.apply(op1.matrix[i][j], op2.matrix[i][j]);
            }

            return result;
        }

        private static int[][] clone2dArray(int matrix[][]) {
            return Arrays.stream(matrix)
                    .parallel()
                    .map(int[]::clone)
                    .toArray(int[][]::new);
        }

        private static String matrixToString(int matrix[][]) {
            StringJoiner joiner = new StringJoiner("\n");
            for (int row[]: matrix) {
                StringJoiner rowJoiner = new StringJoiner(" ", "[", "]");
                for (int j = 0; j < matrix[0].length; j++)
                    rowJoiner.add(String.format("%d", row[j]));
                joiner.add(rowJoiner.toString());
            }
            return joiner.toString();
        }
    }
}
