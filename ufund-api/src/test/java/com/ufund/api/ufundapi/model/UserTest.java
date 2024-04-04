package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the User class
 * 
 * @author Graden Olson
 */
@Tag("Model-tier")
public class UserTest {
    @Test
    public void testCtor() {
        // setup
        String expected_name = "Gradono";
        String expected_password = "h";

        // invoke
        User user = new User(expected_name, expected_password);

        // analyze
        assertEquals(expected_name, user.getUsername());
    }

    @Test
    public void testName(){
        // setup
        User user = new User("Gradono", "h");

        String expected_name = "Bill";

        // invoke
        user.setUsername(expected_name);

        // analyze
        assertEquals(expected_name, user.getUsername());
    }

    @Test
    public void testToString() {
        // setup
        String username = "Gradono";
        String expected_string = String.format(User.STRING_FORMAT, username);
        User user = new User(username, "h");

        // invoke
        String actual_string = user.toString();

        // analyze
        assertEquals(expected_string, actual_string);
    }
}
