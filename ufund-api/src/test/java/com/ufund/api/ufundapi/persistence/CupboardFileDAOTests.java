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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests the CupboardFileDAO class
 * 
 * @author Yat Long Chan
 */
@Tag("persistence")
public class CupboardFileDAOTests {
    CupboardFileDAO dao;
    Need[] testNeeds;
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws IOException {
        objectMapper = mock(ObjectMapper.class);
        testNeeds = new Need[3];
        testNeeds[0] = new Need(0, "weapons", 10, 11);
        testNeeds[1] = new Need(1, "supersuit", 20, 1);
        testNeeds[2] = new Need(2, "secret lair", 30, 31);

        when(objectMapper
            .readValue(new File("test.txt"), Need[].class))
            .thenReturn(testNeeds);
        dao = new CupboardFileDAO("test.txt", objectMapper);
    }

    @Test
    public void testDeleteNeedNotFound() throws IOException {
        // invoke
        boolean result = assertDoesNotThrow(() -> dao.deleteNeed(100), "deleteNeed should not throw an exception");
        
        // analyze
        assertEquals(false, result); // check that the delete was not successful
        assertEquals(testNeeds.length, dao.needs.size()); // check that the size of the needs map has not changed
    }

    @Test
    public void testDeleteNeed() throws IOException {
        // invoke
        boolean result = assertDoesNotThrow(() -> dao.deleteNeed(1), "deleteNeed should not throw an exception");
        
        // analyze
        assertEquals(true, result); // check that the delete was successful
        assertEquals(testNeeds.length - 1, dao.needs.size()); // check that the size of the needs map has decreased
    }

    @Test
    public void testGetNeedNotFound() throws IOException {
        // invoke
        Need result = assertDoesNotThrow(() -> dao.getNeed(100), "getNeed should not throw an exception");
        
        // analyze
        assertNull(result); // check that the need was not found
    }

    @Test
    public void testGetNeed() throws IOException {
        // invoke
        Need result = assertDoesNotThrow(() -> dao.getNeed(1), "getNeed should not throw an exception");
        
        // analyze
        assertNotNull(result); // check that the need was found
        assertEquals(testNeeds[1], result); // check that the need is the expected need
    }
}