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
 * @author Ben Hemmers
 */

 @RestController
 @RequestMapping("helper")
public class HelperController {
    private static final Logger LOG = Logger.getLogger(CupboardController.class.getName());
    private HelperDAO helperDAO;

    /**
     * Creates a REST API Controller to respond to requests for the Helper resource
     * 
     */
    public HelperController(HelperDAO helperDAO) {
        this.helperDAO = helperDAO;
    }
    
    @GetMapping("/basket")
    public ResponseEntity<Set<Need>> viewFundingBasket(@RequestParam("user") User user) {
        LOG.info("GET /basket");

        try {
            // Retrieve the funding basket for the specified user
            Set<Need> fundingBasket = user.getBasket();

            // Return the funding basket as a response
            return new ResponseEntity<Set<Need>>(fundingBasket, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
