package com.ufund.api.ufundapi.controller;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Io;
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

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.persistence.CupboardDAO;
import com.ufund.api.ufundapi.persistence.HelperDAO;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the REST API requests for the Helper/Funding Basket resources
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author Benjamin Hemmers
 */

 @RestController
 @RequestMapping("helper")
public class HelperController {
    private static final Logger LOG = Logger.getLogger(CupboardController.class.getName());
    private HelperDAO helperDAO;

    /**
     * Creates a REST API Controller to respond to requests for the Helper resource
     */
    public HelperController(HelperDAO helperDAO) {
        this.helperDAO = helperDAO;
    }
    
    @GetMapping("/{username}/basket")
    public ResponseEntity<?> getFundingBasket(@PathVariable String username) {
        LOG.info("GET /helper/basket");

        try {
            // Get the user by username
            User user = helperDAO.getUser(username);

            // Get the funding basket for the given user
            Set<Need> fundingBasket = helperDAO.getFundingBasket(user);

            // Return the funding basket as a response
            return ResponseEntity.ok(fundingBasket);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while getting funding basket");
        }
    }
}
