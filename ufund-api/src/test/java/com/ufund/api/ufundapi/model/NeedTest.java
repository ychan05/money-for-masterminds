package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Hero class
 * 
 * @author Graden Olson
 */
@Tag("Model-tier")
public class NeedTest {
    @Test
    public void testCtor() {
        // setup
        int expected_id = 99;
        String expected_name = "Raygun";
        double expected_price = 29.99;
        int expected_quantity = 1;

        // invoke
        Need need = new Need(expected_id, expected_name, expected_price, expected_quantity);

        // analyze
        assertEquals(expected_id, need.getId());
        assertEquals(expected_name, need.getName());
        assertEquals(expected_price, need.getPrice());
        assertEquals(expected_quantity, need.getQuantity());
    }

    @Test
    public void testName() {
        // setup
        int id = 99;
        String name = "Raygun";
        double price = 29.99;
        int quantity = 1;
        Need need = new Need(id, name, price, quantity);

        String expected_name = "Lava-inator";

        // invoke
        need.setName(expected_name);

        // analyze
        assertEquals(expected_name, need.getName());
    }

    @Test
    public void testPrice() {
        // setup
        int id = 99;
        String name = "Raygun";
        double price = 29.99;
        int quantity = 1;
        Need need = new Need(id, name, price, quantity);

        double expected_price = 39.99;

        // invoke
        need.setPrice(expected_price);

        // analyze
        assertEquals(expected_price, need.getPrice());
    }

    @Test
    public void testQuantity(){
        // setup
        int id = 99;
        String name = "Raygun";
        double price = 29.99;
        int quantity = 1;
        Need need = new Need(id, name, price, quantity); 

        int expected_quantity = 50;

        // invoke
        need.setQuantity(expected_quantity);

        // analyze
        assertEquals(expected_quantity, need.getQuantity());
    }

    @Test
    public void testToString() {
        // setup
        int id = 99;
        String name = "Raygun";
        double price = 29.99;
        int quantity = 1;
        String expected_string = String.format(Need.STRING_FORMAT, id, name, price, quantity);
        Need need = new Need(id, name, price, quantity);

        // invoke
        String actual_string = need.toString();

        // analyze
        assertEquals(expected_string, actual_string);
    }
}
