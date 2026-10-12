package lab1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComplexMatrixTest {

    @Test
    void testCreateMatrix() {
        complex[][] data = {
            {new complex(1, 2), new complex(3, 4)},
            {new complex(5, 6), new complex(7, 8)}
        };
        complex_matrix matrix = new complex_matrix(data);

        assertEquals(2, matrix.rows());
        assertEquals(2, matrix.columns());
        assertEquals(new complex(1, 2), matrix.get(0, 0));
        assertEquals(new complex(3, 4), matrix.get(0, 1));
        assertEquals(new complex(5, 6), matrix.get(1, 0));
        assertEquals(new complex(7, 8), matrix.get(1, 1));
    }

    @Test
    void testCreateZeroMatrix() {
        complex[][] data = {
            {new complex(0, 0), new complex(0, 0)},
            {new complex(0, 0), new complex(0, 0)}
        };
        complex_matrix matrix = new complex_matrix(data);

        assertEquals(2, matrix.rows());
        assertEquals(2, matrix.columns());
        assertEquals(new complex(0, 0), matrix.get(0, 0));
        assertEquals(new complex(0, 0), matrix.get(0, 1));
        assertEquals(new complex(0, 0), matrix.get(1, 0));
        assertEquals(new complex(0, 0), matrix.get(1, 1));
    }

    @Test
    void testCreateNegativeMatrix() {
        complex[][] data = {
            {new complex(-1, -2), new complex(-3, -4)}
        };
        complex_matrix matrix = new complex_matrix(data);

        assertEquals(1, matrix.rows());
        assertEquals(2, matrix.columns());
        assertEquals(new complex(-1, -2), matrix.get(0, 0));
        assertEquals(new complex(-3, -4), matrix.get(0, 1));
    }

    @Test
    void testMatrixCopiesInputArray() {
        complex[][] data = {{new complex(1, 2)}};
        complex_matrix matrix = new complex_matrix(data);
        data[0][0] = new complex(99, 99);

        assertEquals(new complex(1, 2), matrix.get(0, 0));
    }

    @Test
    void testNullMatrix() {
        assertThrows(IllegalArgumentException.class, () -> new complex_matrix(null));
    }

    @Test
    void testEmptyMatrix() {
        complex[][] data = {};
        assertThrows(IllegalArgumentException.class, () -> new complex_matrix(data));
    }

    @Test
    void testNullFirstRow() {
        complex[][] data = {null};
        assertThrows(IllegalArgumentException.class, () -> new complex_matrix(data));
    }

    @Test
    void testEmptyFirstRow() {
        complex[][] data = {{} };
        assertThrows(IllegalArgumentException.class, () -> new complex_matrix(data));
    }

    @Test
    void testNullLaterRow() {
        complex[][] data = {
            {new complex(1, 0)},
            null
        };
        assertThrows(IllegalArgumentException.class, () -> new complex_matrix(data));
    }

    @Test
    void testDifferentRowLengths() {
        complex[][] data = {
            {new complex(1, 0), new complex(2, 0)},
            {new complex(3, 0)}
        };
        assertThrows(IllegalArgumentException.class, () -> new complex_matrix(data));
    }

    @Test
    void testNullElement() {
        complex[][] data = {{new complex(1, 0), null}};
        assertThrows(IllegalArgumentException.class, () -> new complex_matrix(data));
    }

    @Test
    void testGetOutsideMatrix() {
        complex_matrix matrix = new complex_matrix(new complex[][] {
            {new complex(1, 0), new complex(2, 0)}
        });

        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(0, 2));
    }

    @Test
    void testAddition() {
        complex_matrix a = new complex_matrix(new complex[][] {
            {new complex(1, 2), new complex(3, 0)},
            {new complex(0, -1), new complex(2, 3)}
        });
        complex_matrix b = new complex_matrix(new complex[][] {
            {new complex(4, -1), new complex(1, 2)},
            {new complex(2, 3), new complex(-2, 1)}
        });

        complex_matrix result = a.add(b);
        assertEquals(new complex(5, 1), result.get(0, 0));
        assertEquals(new complex(4, 2), result.get(0, 1));
        assertEquals(new complex(2, 2), result.get(1, 0));
        assertEquals(new complex(0, 4), result.get(1, 1));
    }

    @Test
    void testAdditionWithZeroMatrix() {
        complex_matrix a = new complex_matrix(new complex[][] {{new complex(2, 3)}});
        complex_matrix zero = new complex_matrix(new complex[][] {{new complex(0, 0)}});

        assertEquals(new complex(2, 3), a.add(zero).get(0, 0));
    }

    @Test
    void testAdditionDifferentSizes() {
        complex_matrix a = new complex_matrix(new complex[][] {
            {new complex(1, 0), new complex(2, 0)}
        });
        complex_matrix b = new complex_matrix(new complex[][] {
            {new complex(1, 0)},
            {new complex(2, 0)}
        });

        assertThrows(IllegalArgumentException.class, () -> a.add(b));
    }

    @Test
    void testSubtraction() {
        complex_matrix a = new complex_matrix(new complex[][] {
            {new complex(1, 2), new complex(3, 0)},
            {new complex(0, -1), new complex(2, 3)}
        });
        complex_matrix b = new complex_matrix(new complex[][] {
            {new complex(4, -1), new complex(1, 2)},
            {new complex(2, 3), new complex(-2, 1)}
        });

        complex_matrix result = a.subtract(b);
        assertEquals(new complex(-3, 3), result.get(0, 0));
        assertEquals(new complex(2, -2), result.get(0, 1));
        assertEquals(new complex(-2, -4), result.get(1, 0));
        assertEquals(new complex(4, 2), result.get(1, 1));
    }

    @Test
    void testSubtractionDifferentSizes() {
        complex_matrix a = new complex_matrix(new complex[][] {{new complex(1, 0)}});
        complex_matrix b = new complex_matrix(new complex[][] {{new complex(1, 0), new complex(2, 0)}});

        assertThrows(IllegalArgumentException.class, () -> a.subtract(b));
    }

    @Test
    void testMultiplication() {
        complex_matrix a = new complex_matrix(new complex[][] {
            {new complex(1, 1), new complex(2, 0)},
            {new complex(0, 0), new complex(1, -1)}
        });
        complex_matrix b = new complex_matrix(new complex[][] {
            {new complex(1, 0), new complex(0, 1)},
            {new complex(2, 0), new complex(1, 0)}
        });

        complex_matrix result = a.multiply(b);
        assertEquals(new complex(5, 1), result.get(0, 0));
        assertEquals(new complex(1, 1), result.get(0, 1));
        assertEquals(new complex(2, -2), result.get(1, 0));
        assertEquals(new complex(1, -1), result.get(1, 1));
    }

    @Test
    void testMultiplicationRectangularMatrices() {
        complex_matrix a = new complex_matrix(new complex[][] {
            {new complex(1, 0), new complex(2, 0), new complex(3, 0)},
            {new complex(4, 0), new complex(5, 0), new complex(6, 0)}
        });
        complex_matrix b = new complex_matrix(new complex[][] {
            {new complex(7, 0), new complex(8, 0)},
            {new complex(9, 0), new complex(10, 0)},
            {new complex(11, 0), new complex(12, 0)}
        });

        complex_matrix result = a.multiply(b);
        assertEquals(2, result.rows());
        assertEquals(2, result.columns());
        assertEquals(new complex(58, 0), result.get(0, 0));
        assertEquals(new complex(64, 0), result.get(0, 1));
        assertEquals(new complex(139, 0), result.get(1, 0));
        assertEquals(new complex(154, 0), result.get(1, 1));
    }

    @Test
    void testMultiplicationByIdentityMatrix() {
        complex_matrix a = new complex_matrix(new complex[][] {
            {new complex(1, 2), new complex(3, 4)},
            {new complex(5, 6), new complex(7, 8)}
        });
        complex_matrix identity = new complex_matrix(new complex[][] {
            {new complex(1, 0), new complex(0, 0)},
            {new complex(0, 0), new complex(1, 0)}
        });

        complex_matrix result = a.multiply(identity);
        assertEquals(new complex(1, 2), result.get(0, 0));
        assertEquals(new complex(3, 4), result.get(0, 1));
        assertEquals(new complex(5, 6), result.get(1, 0));
        assertEquals(new complex(7, 8), result.get(1, 1));
    }

    @Test
    void testMultiplicationIncompatibleSizes() {
        complex_matrix a = new complex_matrix(new complex[][] {
            {new complex(1, 0), new complex(2, 0)}
        });
        complex_matrix b = new complex_matrix(new complex[][] {
            {new complex(3, 0), new complex(4, 0)}
        });

        assertThrows(IllegalArgumentException.class, () -> a.multiply(b));
    }

    @Test
    void testTransposeRectangularMatrix() {
        complex_matrix a = new complex_matrix(new complex[][] {
            {new complex(1, 1), new complex(2, -1), new complex(3, 0)},
            {new complex(4, 2), new complex(5, 1), new complex(6, -3)}
        });

        complex_matrix result = a.transpose();
        assertEquals(3, result.rows());
        assertEquals(2, result.columns());
        assertEquals(new complex(1, 1), result.get(0, 0));
        assertEquals(new complex(4, 2), result.get(0, 1));
        assertEquals(new complex(2, -1), result.get(1, 0));
        assertEquals(new complex(5, 1), result.get(1, 1));
        assertEquals(new complex(3, 0), result.get(2, 0));
        assertEquals(new complex(6, -3), result.get(2, 1));
    }

    @Test
    void testDoubleTranspose() {
        complex_matrix a = new complex_matrix(new complex[][] {
            {new complex(1, 2), new complex(3, 4)}
        });

        complex_matrix result = a.transpose().transpose();
        assertEquals(1, result.rows());
        assertEquals(2, result.columns());
        assertEquals(new complex(1, 2), result.get(0, 0));
        assertEquals(new complex(3, 4), result.get(0, 1));
    }

    @Test
    void testDeterminantOneByOne() {
        complex_matrix a = new complex_matrix(new complex[][] {{new complex(3, 4)}});
        assertEquals(new complex(3, 4), a.determinant());
    }

    @Test
    void testDeterminantTwoByTwo() {
        complex_matrix a = new complex_matrix(new complex[][] {
            {new complex(1, 1), new complex(2, 0)},
            {new complex(3, 0), new complex(4, -1)}
        });
        assertEquals(new complex(-1, 3), a.determinant());
    }

    @Test
    void testDeterminantThreeByThree() {
        complex_matrix a = new complex_matrix(new complex[][] {
            {new complex(1, 0), new complex(2, 0), new complex(3, 0)},
            {new complex(0, 0), new complex(1, 0), new complex(4, 0)},
            {new complex(5, 0), new complex(6, 0), new complex(0, 0)}
        });
        assertEquals(new complex(1, 0), a.determinant());
    }

    @Test
    void testDeterminantFourByFour() {
        complex_matrix a = new complex_matrix(new complex[][] {
            {new complex(2, 0), new complex(0, 0), new complex(0, 0), new complex(0, 0)},
            {new complex(0, 0), new complex(3, 0), new complex(0, 0), new complex(0, 0)},
            {new complex(0, 0), new complex(0, 0), new complex(4, 0), new complex(0, 0)},
            {new complex(0, 0), new complex(0, 0), new complex(0, 0), new complex(5, 0)}
        });
        assertEquals(new complex(120, 0), a.determinant());
    }

    @Test
    void testDeterminantOfSingularMatrix() {
        complex_matrix a = new complex_matrix(new complex[][] {
            {new complex(1, 0), new complex(2, 0)},
            {new complex(2, 0), new complex(4, 0)}
        });
        assertEquals(new complex(0, 0), a.determinant());
    }

    @Test
    void testDeterminantOfNonSquareMatrix() {
        complex_matrix a = new complex_matrix(new complex[][] {
            {new complex(1, 0), new complex(2, 0)}
        });
        assertThrows(IllegalArgumentException.class, a::determinant);
    }
}
