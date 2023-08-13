package br.com.monteiro;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Order(3)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodOrderedByOrdererIndexTest {

    @Test
    @Order(1)
    void testD(){
        System.out.println("Running Test D");
    }
    @Test
    @Order(3)
    void testB(){
        System.out.println("Running Test B");
    }
    @Test
    @Order(2)
    void testC(){
        System.out.println("Running Test C");
    }
    @Test
    @Order(4)
    void testA(){
        System.out.println("Running Test A");
    }

}
