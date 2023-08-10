package br.com.monteiro.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math Operations in SimpleMath Class")
public class SimpleMathTest {

    @Test
    @DisplayName("Test 6.2 + 2 = 8.2")
    void testSum() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 6.2D;
        double secondNumber = 2D;

        double actual = math.sum(firstNumber, secondNumber);
        double expected = 8.2D;

        assertEquals(expected, actual, () -> firstNumber +
                "+" + secondNumber + " did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }

    @Test
    @DisplayName("Test 6.2 - 2 = 4.2")
    void testSubtraction() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 6.2D;
        double secondNumber = 2D;

        double actual = math.subtraction(firstNumber, secondNumber);
        double expected = 4.2D;

        assertEquals(expected, actual, () -> firstNumber +
                "-" + secondNumber + " did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }

    @Test
    @DisplayName("Test 6.2 * 2 = 12.4")
    void testMultiplication() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 6.2D;
        double secondNumber = 2D;

        double actual = math.multiplication(firstNumber, secondNumber);
        double expected = 12.4D;

        assertEquals(expected, actual, () -> firstNumber +
                "*" + secondNumber + " did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }

    @Test
    @DisplayName("Test 6.2 / 2 = 3.1")
    void testDivision() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 6.2D;
        double secondNumber = 2D;

        double actual = math.division(firstNumber, secondNumber);
        double expected = 3.1D;

        assertEquals(expected, actual, () -> firstNumber +
                "/" + secondNumber + " did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }

//    @Test
//    @DisplayName("Test Division by Zero")
//    void testDivision_When_FirstNumberIsDividedByZero_ShouldThrowArithmeticException() {
//        Assertions.fail();
//    }

    @Test
    @DisplayName("Test (6.2 +2) / 2 = 4.1")
    void testMean() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 6.2D;
        double secondNumber = 2D;

        double actual = math.mean(firstNumber, secondNumber);
        double expected = 4.1D;

        assertEquals(expected, actual, () -> "(" + firstNumber +
                "+" + secondNumber + ")/2" + "did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }

    @Test
    @DisplayName("Test Square Root of 81 = 9")
    void testSquareRoot() {
        SimpleMath math = new SimpleMath();
        double number = 81D;
        double expected = 9D;

        double actual = math.squareRoot(number);

        assertEquals(expected, actual,
                () -> "SquareRoot of " + number +
                        " did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }


}
