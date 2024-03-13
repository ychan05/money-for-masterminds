package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.ufund.api.ufundapi.persistence.HelperDAO;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Io;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test Controller for Helper
 * 
 * @author Yat Long Chan
 */
public class HelperControllerTest {
    private HelperController controller;
    private HelperDAO dao;
    
    @BeforeEach
    public void setup() {
        dao = mock(HelperDAO.class); // simulate the DAO
        controller = new HelperController(dao);
    }

    @Test
    public void testGetFundingBasket() throws IOException {
        // setup
        User user = new User("testUser");
        
        // simulate success
        when(dao.getFundingBasket(user)).thenReturn(new HashSet<Need>());
        
        // invoke
        ResponseEntity<?> response = controller.getFundingBasket(user.getUsername());

        // analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
