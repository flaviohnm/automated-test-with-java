package br.com.monteiro.business;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturn10() {

        //Given
        List<?> list = mock(List.class);

        //When
        when(list.size()).thenReturn(10);

        //When
        assertEquals(10, list.size());

    }

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues() {

        //Given
        List<?> list = mock(List.class);

        //When
        when(list.size()).thenReturn(10).thenReturn(10).thenReturn(20);

        //When
        assertEquals(10, list.size());
        assertEquals(10, list.size());
        assertEquals(20, list.size());

    }

    @Test
    void testMockingList_When_GetIsCalled_ShouldReturnMonteiro() {

        //Given
        var list = mock(List.class);

        //When
        when(list.get(0)).thenReturn("Monteiro");

        //When
        assertEquals("Monteiro", list.get(0));
        assertNull(list.get(1));

    }

    @Test
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturn() {

        //Given
        var list = mock(List.class);


        //If you are using argument matchers, all arguments
        //have to be provided by matchers.
        when(list.get(anyInt())).thenReturn("Monteiro");

        //When
        assertEquals("Monteiro", list.get(anyInt()));
        assertEquals("Monteiro",list.get(anyInt()));

    }


    @Test
    void testMockingList_When_ThrowsAnException() {

        //Given
        var list = mock(List.class);


        //If you are using argument matchers, all arguments
        //have to be provided by matchers.
        when(list.get(anyInt())).thenThrow(new RuntimeException("Foo Bar!!"));

        //When
        assertThrows(RuntimeException.class,() -> {
            list.get(anyInt());},
                () -> "Should have thrown an RuntimesException");

    }
}
