package lab1;

public class complex_matrix {
    private complex[][] matrix;

    public complex_matrix(complex[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("матрица пустая");
        }

        if (matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException("строка матрицы пустая");
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == null) {
                throw new IllegalArgumentException("строка матрицы пустая");
            }

            if (matrix[i].length != matrix[0].length) {
                throw new IllegalArgumentException("у строк разная длина");
            }

            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == null) {
                    throw new IllegalArgumentException("пустой элемент матрицы");
                }
            }
        }

        this.matrix = new complex[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public int rows() {
        return matrix.length;
    }

    public int columns() {
        return matrix[0].length;
    }

    public complex get(int row, int column) {
        if (row < 0 || row >= rows() || column < 0 || column >= columns()) {
            throw new IndexOutOfBoundsException("такого элемента нет");
        }

        return matrix[row][column];
    }

    public complex_matrix add(complex_matrix other) {
        if (rows() != other.rows() || columns() != other.columns()) {
            throw new IllegalArgumentException("размеры матриц не совпадают");
        }

        complex[][] result = new complex[rows()][columns()];

        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < columns(); j++) {
                result[i][j] = matrix[i][j].add(other.get(i, j));
            }
        }

        return new complex_matrix(result);
    }

    public complex_matrix subtract(complex_matrix other) {
        if (rows() != other.rows() || columns() != other.columns()) {
            throw new IllegalArgumentException("размеры матриц не совпадают");
        }

        complex[][] result = new complex[rows()][columns()];

        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < columns(); j++) {
                result[i][j] = matrix[i][j].subtract(other.get(i, j));
            }
        }

        return new complex_matrix(result);
    }

    public complex_matrix multiply(complex_matrix other) {
        if (columns() != other.rows()) {
            throw new IllegalArgumentException("эти матрицы нельзя умножить");
        }

        complex[][] result = new complex[rows()][other.columns()];

        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < other.columns(); j++) {
                complex sum = new complex(0, 0);

                for (int k = 0; k < columns(); k++) {
                    complex number = matrix[i][k].multiply(other.get(k, j));
                    sum = sum.add(number);
                }

                result[i][j] = sum;
            }
        }

        return new complex_matrix(result);
    }

    public complex_matrix transpose() {
        complex[][] result = new complex[columns()][rows()];

        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < columns(); j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return new complex_matrix(result);
    }

    public complex determinant() {
        if (rows() != columns()) {
            throw new IllegalArgumentException("матрица должна быть квадратной");
        }

        int n = rows();

        if (n == 1) {
            return matrix[0][0];
        }

        if (n == 2) {
            complex first = matrix[0][0].multiply(matrix[1][1]);
            complex second = matrix[0][1].multiply(matrix[1][0]);

            return first.subtract(second);
        }

        complex det = new complex(0, 0);

        for (int j = 0; j < n; j++) {
            complex[][] small_matrix = new complex[n - 1][n - 1];

            for (int row = 1; row < n; row++) {
                int new_col = 0;

                for (int col = 0; col < n; col++) {
                    if (col == j) {
                        continue;
                    }

                    small_matrix[row - 1][new_col] = matrix[row][col];
                    new_col++;
                }
            }

            complex_matrix small = new complex_matrix(small_matrix);
            complex number = matrix[0][j].multiply(small.determinant());

            if (j % 2 == 0) {
                det = det.add(number);
            } else {
                det = det.subtract(number);
            }
        }

        return det;
    }
}