package br.com.monteiro.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DemoRepeatedTest {

    SimpleMath math;

    @BeforeEach
    void beforeEachMethod() {
        math = new SimpleMath();
        System.out.println("Running @BeforeEach method");
    }


    @RepeatedTest(3)
    @DisplayName("Test Division by Zero")
    void testDivision_When_FirstNumberIsDividedByZero_ShouldThrowArithmeticException(
            RepetitionInfo repetitionInfo) {

        System.out.println("Repetition # "+ repetitionInfo.getCurrentRepetition()
                        + " of " + repetitionInfo.getTotalRepetitions());

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


    @RepeatedTest(value = 3, name = "{displayName}. Repetiiton "
            + "{currentRepetition} of {totalRepetitions}!")
    @DisplayName("Test Division by Zero | Two")
    void testDivision_When_FirstNumberIsDividedByZero_ShouldThrowArithmeticExceptionTwo(
            RepetitionInfo repetitionInfo) {

        System.out.println("Repetition # "+ repetitionInfo.getCurrentRepetition()
                + " of " + repetitionInfo.getTotalRepetitions());

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

}
