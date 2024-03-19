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
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@Tag("persistence")
public class HelperFileDAOTest {
    HelperFileDAO dao;
    User[] testUsers;    
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws IOException {
        objectMapper = mock(ObjectMapper.class);
        testUsers = new User[4];
        testUsers[0] = new User("Gradono");
        testUsers[1] = new User("Bill");
        testUsers[2] = new User("Phil");
        testUsers[3] = new User("Krill");

        when(objectMapper
            .readValue(new File("test.txt"), User[].class))
            .thenReturn(testUsers);
        dao = new HelperFileDAO("test.txt", objectMapper);
    }

    @Test
    public void testGetFundingBasket() throws IOException {
        User user = new User("testUser");
        Need need = new Need("test", 10, 1);
        user.addToBasket(need);

        Set<Need> basket = dao.getFundingBasket(user);

        assertNotNull(basket);
        assertTrue(basket.contains(need));
    }

    @Test
    public void testGetUser() throws IOException {
        User user = new User("Gradono");

        User result = dao.getUser(user.getUsername());

        assertNotNull(result);
        assertEquals(user.getBasket(), result.getBasket());
    }

    @Test
    public void testAddNeedToBasket() throws IOException {
        User user = new User("testUser");
        Need need = new Need("test", 10, 10);

        dao.addNeedToBasket(user, need);

        assertTrue(user.getBasket().contains(need));
    }

    @Test
    public void testRemoveNeedFromBasket() throws IOException {
        User user = new User("testUser");
        Need need = new Need("test", 10, 10);
        user.addToBasket(need);

        dao.removeNeedFromBasket(user, need);

        assertFalse(user.getBasket().contains(need));
    }
}

