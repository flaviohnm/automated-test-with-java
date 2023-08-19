package br.com.monteiro.business;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;


public class ListWithBDDTest {

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturn10() {

        //Given
        List<?> list = mock(List.class);

        //When
        given(list.size()).willReturn(10);

        //When
        assertThat(list.size(), is(10));

    }

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues() {

        //Given
        List<?> list = mock(List.class);

        //When
        given(list.size()).willReturn(10).willReturn(10).willReturn(20);

        //When
        assertThat(list.size(), is(10));
        assertThat(list.size(), is(10));
        assertThat(list.size(), is(20));

    }

    @Test
    void testMockingList_When_GetIsCalled_ShouldReturnMonteiro() {

        //Given
        var list = mock(List.class);

        //When
        given(list.get(0)).willReturn("Monteiro");

        //When
        assertThat(list.get(0), is("Monteiro"));
        assertNull(list.get(1));

    }

    @Test
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturn() {

        //Given
        var list = mock(List.class);


        //If you are using argument matchers, all arguments
        //have to be provided by matchers.
        given(list.get(anyInt())).willReturn("Monteiro");

        //When
        assertThat(list.get(anyInt()), is("Monteiro"));
        assertThat(list.get(anyInt()), is("Monteiro"));

    }


    @Test
    void testMockingList_When_ThrowsAnException() {

        // Given / Arrange
        var list = mock(List.class);

        // If you are using argument matchers, all arguments
        // have to be provided by matchers.
        given(list.get(anyInt())).willThrow(new RuntimeException("Foo Bar!!"));

        // When / Act & Then / Assert
        assertThrows(RuntimeException.class,
                () -> {
                    // When / Act
                    list.get(anyInt());
                },
                () -> "Should have throw an RuntimeException");
    }
}
