package br.com.monteiro.mockito.staticwithparams;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

public class MyUtilsTest {

    @Test
    void shouldMockStaticMethodWithParams(){
        try(MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class)){
            mockedStatic.when(
                    () -> MyUtils.getWelcomeMessage(
                            eq("Monteiro"),
                            anyBoolean())).thenReturn("Howdy Monteiro!");

            String result = MyUtils.getWelcomeMessage("Monteiro", false);

            assertEquals("Howdy Monteiro!", result);

        }

    }
}
