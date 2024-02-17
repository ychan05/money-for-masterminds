package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

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
 * @author Yat Long Chan
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
    public void deleteNeedError() throws IOException {
        // setup
        int id = 1;

        // simulate error
        doThrow(new IOException()).when(dao).deleteNeed(id);
        
        // invoke
        ResponseEntity<?> response = controller.deleteNeed(id);

        // analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
