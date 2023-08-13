package br.com.monteiro.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math Operations in SimpleMath Class")
public class SimpleMathTestS4 {

    SimpleMath math;

    @BeforeEach
    void beforeEachMethod() {
        math = new SimpleMath();
    }

    @DisplayName("Test double sbtraction [firstNumber, secondNumber, expected]")
    @ParameterizedTest
    @MethodSource("testDivisionInputParameters")
    void testDivision(double firstNumber, double secondNumber, double expected) {

        //Given
        double actual = math.division(firstNumber, secondNumber);

        assertEquals(expected, actual, 2D, () -> firstNumber + "/" + secondNumber + " did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }

    @DisplayName("Test double sbtraction [firstNumber, secondNumber, expected]")
    @ParameterizedTest
    @CsvSource({
            "6.2, 2, 3.1",
            "71, 14, 5.07",
            "18.3, 3.1, 5.90"
    })
    void testDivisionCsvSource(double firstNumber, double secondNumber, double expected) {

        //Given
        double actual = math.division(firstNumber, secondNumber);

        assertEquals(expected, actual, 2D, () -> firstNumber + "/" + secondNumber + " did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }

    @DisplayName("Test double sbtraction [firstNumber, secondNumber, expected]")
    @ParameterizedTest
    @CsvFileSource(resources = "/testDivision.csv")
    void testDivisionCsvFile(double firstNumber, double secondNumber, double expected) {

        //Given
        double actual = math.division(firstNumber, secondNumber);

        assertEquals(expected, actual, 2D, () -> firstNumber + "/" + secondNumber + " did not produce " + expected + "!");
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

    @ParameterizedTest
    @ValueSource(strings = {"Pele", "Senna", "Keith Moon"})
    void testValueSource(String firstName){
        System.out.println(firstName);
        assertNotNull(firstName);
    }

    public static Stream<Arguments> testDivisionInputParameters() {
        return Stream.of(
                Arguments.of(6.2D, 2D, 3.1D),
                Arguments.of(71D, 14D, 5.07D),
                Arguments.of(18.3, 3.1D, 5.90)
        );
    }

}
