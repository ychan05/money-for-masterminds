package com.ufund.api.ufundapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ufund.api.ufundapi.persistence.AuthenticatorDAO;
import com.ufund.api.ufundapi.model.User;

/**
 * Handles the REST API requests for the User resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author Graden Olson
 */

@RestController
@RequestMapping("authenticator")
public class AuthenticatorController {
    private static final Logger LOG = Logger.getLogger(AuthenticatorController.class.getName());
    private AuthenticatorDAO authenticatorDAO;

    /**
     * Creates a REST API Controller to respond to requests for the User resource
     * @param authenticatorDAO
     */
    public AuthenticatorController(AuthenticatorDAO authenticatorDAO){
        this.authenticatorDAO = authenticatorDAO;
    }

    @PostMapping("")
    public ResponseEntity<User> signin(@RequestBody User user){
        LOG.info("POST /authenticator/ " + user);

        try{
            User newUser = authenticatorDAO.signin(user);
            if(newUser == null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            else return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}/{password}")
    public ResponseEntity<User> login(@PathVariable String username, @PathVariable String password) {
        LOG.info("GET /authenticator/" + username + "/" + password);

        try {
            User user = authenticatorDAO.login(username, password);
            if(user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
        catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
