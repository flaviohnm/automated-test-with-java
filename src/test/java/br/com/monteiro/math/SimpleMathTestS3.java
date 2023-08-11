package br.com.monteiro.math;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math Operations in SimpleMath Class")
public class SimpleMathTestS3 {

    SimpleMath math;

    @BeforeAll
    static void setup() {
        System.out.println("Running @BeforeAll method");
    }

    @AfterAll
    static void cleanup() {
        System.out.println("Running @AfterAll method");
    }

    @BeforeEach
    void beforeEachMethod() {
        math = new SimpleMath();
        System.out.println("Running @BeforeEach method");
    }

    @AfterEach
    void afterEachMethod() {
        System.out.println("Running @AfterEach method");
    }

    @Test
    @DisplayName("Test 6.2 + 2 = 8.2")
    void testSum() {

        //Given
        double firstNumber = 6.2D;
        double secondNumber = 2D;
        double expected = 8.2D;

        //When
        double actual = math.sum(firstNumber, secondNumber);

        //Then
        assertEquals(expected, actual, () -> firstNumber + "+" + secondNumber + " did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }

    @Test
    @DisplayName("Test 6.2 - 2 = 4.2")
    void testSubtraction() {
        //Given
        double firstNumber = 6.2D;
        double secondNumber = 2D;

        double actual = math.subtraction(firstNumber, secondNumber);
        double expected = 4.2D;

        assertEquals(expected, actual, () -> firstNumber + "-" + secondNumber + " did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }

    @Test
    @DisplayName("Test 6.2 * 2 = 12.4")
    void testMultiplication() {

        //Given
        double firstNumber = 6.2D;
        double secondNumber = 2D;

        double actual = math.multiplication(firstNumber, secondNumber);
        double expected = 12.4D;

        assertEquals(expected, actual, () -> firstNumber + "*" + secondNumber + " did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }

    @Test
    @DisplayName("Test 6.2 / 2 = 3.1")
    void testDivision() {

        //Given
        double firstNumber = 6.2D;
        double secondNumber = 2D;

        double actual = math.division(firstNumber, secondNumber);
        double expected = 3.1D;

        assertEquals(expected, actual, () -> firstNumber + "/" + secondNumber + " did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }

    @Test
    @DisplayName("Test Division by Zero")
    void testDivision_When_FirstNumberIsDividedByZero_ShouldThrowArithmeticException() {

        //Given
        double firstNumber = 6.2D;
        double secondNumber = 0D;

        var expectedMessage = "Impossible to divide by zero!";

        //When & Then
        ArithmeticException actual = assertThrows(
                ArithmeticException.class, () -> {
                    math.division(firstNumber, secondNumber);
                }, () -> "Division by zero should throw an ArithmeticException");

        assertEquals(expectedMessage, actual.getMessage(),
                () -> "Unexpected exception message!");
    }

    @Test
    @DisplayName("Test (6.2 +2) / 2 = 4.1")
    void testMean() {

        double firstNumber = 6.2D;
        double secondNumber = 2D;

        double actual = math.mean(firstNumber, secondNumber);
        double expected = 4.1D;

        assertEquals(expected, actual, () -> "(" + firstNumber + "+" + secondNumber + ")/2" + "did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }

    @Test
    @DisplayName("Test Square Root of 81 = 9")
    void testSquareRoot() {

        double number = 81D;
        double expected = 9D;

        double actual = math.squareRoot(number);

        assertEquals(expected, actual, () -> "SquareRoot of " + number + " did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }


}
