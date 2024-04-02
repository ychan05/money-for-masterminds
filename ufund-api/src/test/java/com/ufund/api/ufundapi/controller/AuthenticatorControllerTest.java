package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import com.ufund.api.ufundapi.persistence.AuthenticatorDAO;
import com.ufund.api.ufundapi.persistence.CupboardDAO;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Io;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test Controller for Authenticator
 * 
 * @author Graden Olson
 */
public class AuthenticatorControllerTest {
    private AuthenticatorController controller;
    private AuthenticatorDAO dao;

    @BeforeEach
    public void setup() {
        dao = mock(AuthenticatorDAO.class); // simulate the dao
        controller = new AuthenticatorController(dao);
    }

    @Test
    public void testSignin() throws IOException{
        // setup
        User user = new User("Gradono", "h");

        // simulate success
        when(dao.signin(user)).thenReturn(user);

        // invoke
        ResponseEntity<User> response = controller.signin(user);

        // analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testSigninFailed() throws IOException {
        // setup
        User user = new User("wuhhh", "h");

        // simulate IOException
        doThrow(new IOException()).when(dao).signin(user);

        // invoke
        ResponseEntity<User> response = controller.signin(user);

        // analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testCantSignIn() throws IOException {
        // setup
        User user = new User("wuhhh", "h");

        // simulate failure
        when(dao.signin(user)).thenReturn(null);

        // invoke
        ResponseEntity<User> response = controller.signin(user);

        // analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testLogin() throws IOException {
        // setup
        User user = new User("wuhhhh", "h");

        // simulate success
        when(dao.login(user.getUsername(), user.getPassword())).thenReturn(user);

        // invoke
        ResponseEntity<User> response = controller.login(user.getUsername(), user.getPassword());

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testLoginNotFound() throws IOException {
        // setup
        User user = new User("wuhhh", "h");

        // simulate not found
        when(dao.login(user.getUsername(), user.getPassword())).thenReturn(null);

        // invoke
        ResponseEntity<User> response = controller.login(user.getUsername(), user.getPassword());

        // analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testLoginError() throws IOException {
        // setup
        String username = "wuhhh";
        String password = "h";

        doThrow(new IOException()).when(dao).login(username, password);

        // invoke
        ResponseEntity<User> response = controller.login(username, password);

        // analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
