package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import com.ufund.api.ufundapi.persistence.CupboardDAO;
import com.ufund.api.ufundapi.model.Need;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test Controller for Cupboard
 * 
<<<<<<< HEAD
 * @author Yat Long Chan, Graden Olson
=======
 * @author Yat Long Chan, Ben Hemmers
>>>>>>> origin/get-cupboard
 */
public class CupboardControllerTest {
    private CupboardController controller;
    private CupboardDAO dao;
    
    @BeforeEach
    public void setup() {
        dao = mock(CupboardDAO.class); // simulate the DAO
        controller = new CupboardController(dao);
    }

    @Test
    public void deleteNeed() throws IOException {
        // setup
        int id = 1;
        
        // simulate success
        when(dao.deleteNeed(id)).thenReturn(true);
        
        // invoke
        ResponseEntity<?> response = controller.deleteNeed(id);

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteNeedNotFound() throws IOException {
        // setup
        int id = 1;

        // simulate failure
        when(dao.deleteNeed(id)).thenReturn(false);
        
        // invoke
        ResponseEntity<?> response = controller.deleteNeed(id);

        // analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateNeed() throws IOException{
        // setup
        Need need = new Need(1, "need", 6.00, 345);

        // simulate success
        when(dao.createNeed(need)).thenReturn(need);

        // invoke
        ResponseEntity<Need> response = controller.createNeed(need);

        // analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(need, response.getBody());
    }

    @Test
    public void testCreateNeedFailed() throws IOException{
        // setup
        Need need = new Need(1, "need", 6.00, 345);

        // simulate failure
        when(dao.createNeed(need)).thenReturn(null);

        // invoke
        ResponseEntity<Need> response = controller.createNeed(need);

        // analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateNeedHandleException() throws IOException {
        // setup
        Need need = new Need(1, "need", 6.00, 345);

        // simulate IOException
        doThrow(new IOException()).when(dao).createNeed(need);

        // invoke
        ResponseEntity<Need> response = controller.createNeed(need);

        // analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void getNeed() throws IOException {
        // setup
        Need need = new Need(1, "nuke", 100, 1);

        when(dao.getNeed(need.getId())).thenReturn(need); // simulate a need successfully being found
        
        // invoke
        ResponseEntity<Need> response = controller.getNeed(need.getId()); 

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(need, response.getBody());
    }

    @Test
    public void getNeedNotFound() throws IOException {
        // setup
        int needId = 200;

        when(dao.getNeed(needId)).thenReturn(null); // simulate a need not being found

        // invoke
        ResponseEntity<Need> response = controller.getNeed(needId); 

        // analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getNeedError() throws IOException {
        // setup
        int needId = 200;

        doThrow(new IOException()).when(dao).getNeed(needId); // simulate an error

        // invoke
        ResponseEntity<Need> response = controller.getNeed(needId); 

        // analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testUpdateNeed() throws IOException {
        // setup
        Need need = new Need(0, "What", 39.99, 30);

        when(dao.updateNeed(need)).thenReturn(need);
        ResponseEntity<Need> response = controller.updateNeed(need);
        need.setName("Waaaaaah");
        
        // invoke
        response = controller.updateNeed(need);

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(need, response.getBody());
    }

    @Test
    public void testUpdateNeedFailed() throws IOException {
        // setup
        Need need = new Need("Need", 29.99, 30);

        when(dao.updateNeed(need)).thenReturn(null);

        // invoke
        ResponseEntity<Need> response = controller.updateNeed(need);

        // analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateNeedHandleException() throws IOException {
        // setup
        Need need = new Need(99, "Need", 29.99, 30);

        doThrow(new IOException()).when(dao).updateNeed(need);

        // invoke
        ResponseEntity<Need> response = controller.updateNeed(need);

        // analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testSearchNeeds() throws IOException {
        // setup
        String searchString = "su";
        Need[] needs = new Need[2];
        needs[0] = new Need(99, "supersuit", 20, 1);
        needs[1] = new Need(100, "super weapons", 40.00, 100);

        when(dao.findNeeds(searchString)).thenReturn(needs);

        // invoke
        ResponseEntity<Need[]> response = controller.searchNeeds(searchString);

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(needs, response.getBody());
    }

    @Test
    public void testSearchNeedsHandleException() throws IOException {
        // setup
        String searchString = "woah";

        doThrow(new IOException()).when(dao).findNeeds(searchString);

        // invoke
        ResponseEntity<Need[]> response = controller.searchNeeds(searchString);

        // analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testGetCupboard() throws IOException {
        // setup
        Need[] needs = new Need[2];
        needs[0] = new Need(99, "supersuit", 20, 1);
        needs[1] = new Need(100, "super weapons", 40.00, 100);

        when(dao.getNeeds()).thenReturn(needs);

        // invoke
        ResponseEntity<Need[]> response = controller.getCupboard();

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Check for Status Code 200 (OK)
        //assertEquals(2, response.getBody().length);
        assertEquals("supersuit", response.getBody()[0].getName());
        assertEquals("super weapons", response.getBody()[1].getName());
    }

    @Test
    public void testGetCupboardEmpty() throws IOException {
        // setup
        when(dao.getNeeds()).thenReturn(new Need[0]);

        // invoke
        ResponseEntity<Need[]> response = controller.getCupboard();

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Check for Status Code 200 (OK)
        assertEquals(0, response.getBody().length);
    }
}
