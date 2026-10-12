package lab1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComplexTest {
    @Test
    void testCreateComplex() {
        complex a = new complex(2, 3);

        assertEquals(2, a.real());
        assertEquals(3, a.imaginary());
    }
   
    @Test
    void testCreateNegativeComplex() {
        complex a = new complex(-2, -3);

        assertEquals(-2, a.real());
        assertEquals(-3, a.imaginary());
    }
    
    @Test
    void testCreateZeroComplex() {
        complex a = new complex(0, 0);

        assertEquals(0, a.real());
        assertEquals(0, a.imaginary());
    }

    @Test
    void testAddition() {
        complex a = new complex(2, 3);
        complex b = new complex(4, 5);

        complex result = a.add(b);

        assertEquals(6, result.real());
        assertEquals(8, result.imaginary());
    }

    @Test
    void testSubtract() {
        complex a = new complex(1,1);
        complex b = new complex(1,1);

        complex result = a.subtract(b); 

        assertEquals(0, result.real());
        assertEquals(0, result.imaginary());
    }

    @Test
    void testMultiply() {
        complex a = new complex(3,2);
        complex b = new complex(3,2);

        complex result = a.multiply(b); 

        assertEquals(5, result.real());
        assertEquals(12, result.imaginary());
    }

    @Test 
    void testMultiplyWithZeroComplex() {
        complex a = new complex(1,1);
        complex b = new complex(0,0);

        complex result = a.multiply(b);

        assertEquals(0, result.real());
        assertEquals(0, result.imaginary());
    }

    @Test 
    void testDevide() {
        complex a = new complex(6, 4);
        complex b = new complex(2,2);

        complex result = a.devide(b);

        assertEquals(2.5, result.real());
        assertEquals(-0.5, result.imaginary());
    }

    @Test 
    void testDevideByZeroThrowsException() {
        complex a = new complex(1,1);
        complex b = new complex(0,0);

        assertThrows(ArithmeticException.class, () -> a.devide(b));
    }
}