package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Riddle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class RiddleFileDAOTest {
    RiddleFileDAO dao;
    Riddle[] testRiddles;    
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws IOException {
        objectMapper = mock(ObjectMapper.class);
        testRiddles = new Riddle[4];
        testRiddles[0] = new Riddle(1, "What weighs six ounces, sits in a tree, and is very dangerous?", "A sparrow with a machine gun.");
        testRiddles[1] = new Riddle(2, "When you do not know what I am, I am something, but when you know what I am, I am nothing. What am I?", "A riddle.");
        testRiddles[2] = new Riddle(3, "I speak without a mouth and hear without ears. I have no body, but I come alive with the wind. What am I?", "An echo.");
        testRiddles[3] = new Riddle(4, "I am taken from a mine, and shut up in a wooden case, from which I am never released, and yet I am used by almost every person. What am I?", "Pencil lead.");

        when(objectMapper
            .readValue(new File("test.txt"), Riddle[].class))
            .thenReturn(testRiddles);
        dao = new RiddleFileDAO("test.txt", objectMapper);
    }

    @Test
    public void testDeleteRiddleNotFound() throws IOException {
        // invoke
        boolean result = assertDoesNotThrow(() -> dao.deleteRiddle(100), "deleteRiddle should not throw an exception");
        
        // analyze
        assertEquals(false, result); // check that the delete was not successful
        assertEquals(testRiddles.length, dao.riddles.size()); // check that the size of the riddles map has not changed
    }

    @Test
    public void testDeleteRiddle() throws IOException {
        // invoke
        boolean result = assertDoesNotThrow(() -> dao.deleteRiddle(1), "deleteRiddle should not throw an exception");
        
        // analyze
        assertEquals(true, result); // check that the delete was successful
        assertEquals(testRiddles.length - 1, dao.riddles.size()); // check that the size of the riddles map has decreased by 1
    }
    
    @Test
    public void testCreateRiddle() throws IOException {
        // setup
        Riddle riddle = new Riddle(5, "What has keys but can't open locks?", "A piano.");
        
        // invoke
        Riddle result = assertDoesNotThrow(() -> dao.createRiddle(riddle), "createRiddle should not throw an exception");
        
        // analyze
        assertNotNull(result); // check that the result is not null
        Riddle actual = dao.getRiddle(testRiddles[0].getId());
        assertEquals(actual.getId(), testRiddles[0].getId());
        assertEquals(actual.getAnswer(), testRiddles[0].getAnswer());
        assertEquals(actual.getQuestion(), testRiddles[0].getQuestion());

    }

    @Test
    public void testUpdateRiddle() throws IOException{
        // invoke
        Riddle result = assertDoesNotThrow(() -> dao.updateRiddle(testRiddles[0]), "Unexpected exception thrown");

        // analyze
        assertNotNull(result);
        Riddle actual = dao.getRiddle(testRiddles[0].getId());
        assertEquals(actual, testRiddles[0]);
    }

    @Test
    public void testFindRiddles() throws IOException {
        // invoke
        Riddle[] riddles = dao.findRiddles("tree");
        
        // analyze
        assertEquals(riddles.length, 1);
        assertEquals(riddles[0], testRiddles[0]);
    }

    @Test
    public void testGetNeeds() throws IOException {
        // invoke
        Riddle[] riddles = dao.getRiddles();
        
        // analyze
        assertEquals(riddles.length, 4);
        assertEquals(riddles[0], testRiddles[0]);
        assertEquals(riddles[1], testRiddles[1]);
        assertEquals(riddles[2], testRiddles[2]);
        assertEquals(riddles[3], testRiddles[3]);
    }

    @Test
    public void testGetRiddleNotFound() throws IOException {
        // invoke
        Riddle result = assertDoesNotThrow(() -> dao.getRiddle(100), "getRiddle should not throw an exception");
        
        // analyze
        assertNull(result); // check that the riddle was not found
    }

    @Test
    public void testGetRiddle() throws IOException {
        // invoke
        Riddle result = assertDoesNotThrow(() -> dao.getRiddle(1), "getRiddle should not throw an exception");
        
        // analyze
        assertNotNull(result); // check that the need was found
        assertEquals(testRiddles[0], result); // check that the need is the expected need
    }

}

