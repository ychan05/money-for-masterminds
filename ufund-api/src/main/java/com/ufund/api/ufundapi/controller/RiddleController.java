package com.ufund.api.ufundapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import com.ufund.api.ufundapi.model.Riddle;
import com.ufund.api.ufundapi.persistence.RiddleDAO;

@RestController
@RequestMapping("riddle")
public class RiddleController {
    private static final Logger LOG = Logger.getLogger(RiddleController.class.getName());
    private RiddleDAO riddleDAO;

    public RiddleController(RiddleDAO riddleDAO) {
        this.riddleDAO = riddleDAO;
    }

    @PostMapping("")
    public ResponseEntity<Riddle> createRiddle(@RequestBody Riddle riddle) {
        LOG.info("POST /riddle/ " + riddle);
        
        try {
            Riddle newRiddle = riddleDAO.createRiddle(riddle);
            if (newRiddle == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<Riddle>(newRiddle, HttpStatus.CREATED);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Riddle[]> searchRiddles(@RequestParam String question) {
        LOG.info("GET /riddle/?name="+question);

        try{
            Riddle[] riddles = riddleDAO.findRiddles(question);
            if(riddles != null) {
                return new ResponseEntity<Riddle[]>(riddles, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e){
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Riddle> deleteRiddle(@PathVariable int id) {
        LOG.info("DELETE /riddle/" + id);

        try {
            if (riddleDAO.deleteRiddle(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<Riddle> Riddle(@RequestBody Riddle riddle) {
        LOG.info("PUT /riddle/ " + riddle);

        try{
            Riddle updatedRiddle = riddleDAO.updateRiddle(riddle);
            if(updatedRiddle != null) {
                riddleDAO.updateRiddle(riddle);
                return new ResponseEntity<Riddle>(updatedRiddle, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Riddle> getRiddle(@PathVariable int id) {
        LOG.info("GET /riddle/" + id);

        try {
            Riddle riddle = riddleDAO.getRiddle(id);
            if (riddle != null) {
                return new ResponseEntity<>(riddle, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/riddles")
    public ResponseEntity<Riddle[]> getRiddles() {
        LOG.info("GET /riddle/riddles");
        try {
            // Retrieve needs from the cupboard
            Riddle[] riddles = riddleDAO.getRiddles();

            if (riddles == null || riddles.length == 0) { // If empty, returns empty list and OK status
                return new ResponseEntity<Riddle[]>(new Riddle[0], HttpStatus.OK);
            } else {
                return new ResponseEntity<Riddle[]>(riddles, HttpStatus.OK);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
