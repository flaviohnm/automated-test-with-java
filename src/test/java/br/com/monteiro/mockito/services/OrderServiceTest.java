package br.com.monteiro.mockito.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderServiceTest {

    private final OrderService service = new OrderService();
    private final UUID defaultUUID = UUID.fromString("8d8b30e3-de52-4f1c-a71c-9905a8043dac");
    private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(2023,8,28,8,10);

    @DisplayName("Should Include Randon OrderId When NoOrderId Exists")
    @Test
    void testShouldIncludeRandonOrderId_When_NoOrderIdExists(){

        //Given
        try(MockedStatic<UUID> mockedUUID = mockStatic(UUID.class)){
            mockedUUID.when(UUID::randomUUID).thenReturn(defaultUUID);

            //When
            Order result = service.createOrder("MackBook Pro", 2L, null);

            assertEquals(defaultUUID.toString(), result.getId());
        }

    }

    @DisplayName("Should Include current time when create a new order")
    @Test
    void testShouldIncludeCurrentTime_When_CreateANewOrder(){

        //Given
        try(MockedStatic<LocalDateTime> mockedUUID = mockStatic(LocalDateTime.class)){
            mockedUUID.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);

            //When
            Order result = service.createOrder("MackBook Pro", 2L, null);

            assertEquals(defaultLocalDateTime, result.getCreationDate());
        }

    }
}
