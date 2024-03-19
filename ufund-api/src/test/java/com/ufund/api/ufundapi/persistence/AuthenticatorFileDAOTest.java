package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests the AuthenticatorFileDAO class
 * 
 * @author Graden Olson
 */
@Tag("persistence")
public class AuthenticatorFileDAOTest {
    AuthenticatorFileDAO dao;
    User[] testUsers;
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws IOException {
        objectMapper = mock(ObjectMapper.class);
        testUsers = new User[4];
        testUsers[0] = new User("Gradono");
        testUsers[1] = new User("Bill");
        testUsers[2] = new User("Phil");
        testUsers[3] = new User("Krill");

        when(objectMapper
            .readValue(new File("test.txt"), User[].class))
            .thenReturn(testUsers);
        dao = new AuthenticatorFileDAO("test.txt", objectMapper);
    }

    @Test
    public void testSignin() throws IOException{
        // invoke
        User newUser = new User("hello");
        User result = assertDoesNotThrow(() -> dao.signin(newUser), "Unexpected exception thrown");

        // analyze
        assertNotNull(result);
        User actual = dao.login(newUser.getUsername());
        assertEquals(actual.getUsername(), newUser.getUsername());
        assertEquals(actual.getBasket(), newUser.getBasket());
    }

    @Test
    public void testSignInNotFound() throws IOException{
        // invoke
        User result = assertDoesNotThrow(() -> dao.signin(testUsers[0]), "Unexpected exception thrown");

        // anaalyze
        assertNull(result);
    }


    @Test
    public void testLoginNotFound() throws IOException {
        // invoke
        User result = assertDoesNotThrow(() -> dao.login("waah"), "getUser should not throw an exception");

        // analyze
        assertNull(result); // check that the user was not found
    }

    @Test
    public void testGetUser() throws IOException{
        // invoke
        User result = assertDoesNotThrow(() -> dao.login("Gradono"), "getUser should not throw an exception");

        // analyze
        assertNotNull(result); // check that the user was found
        assertEquals(testUsers[0], result); // check that the user is the expected user
    }
}
