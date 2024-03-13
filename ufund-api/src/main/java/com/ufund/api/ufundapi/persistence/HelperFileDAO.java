package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Implements the functionality for JSON persistence for Helpers
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Benjamin Hemmers
 */
@Component
public class HelperFileDAO implements HelperDAO {
    private static final Logger LOG = Logger.getLogger(HelperFileDAO.class.getName());
    private Set<User> users; // Provides a local cache of the Helper Users
    private ObjectMapper objectMapper; // Provides conversion between User objects and JSON text format written to the file
    private String filename; // Filename to read from and write to

    /**
     * Creates a Helper File Data Access Object
     * 
     * @param filename      Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public HelperFileDAO(@Value("${authenticator.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load(); // Load the Helper Users from the file
    }

    /**
     * Saves the Helper Users into the file as an array of JSON objects
     * 
     * @return true if the Helpers were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        // Serializes the Java Objects to JSON objects into the file
        objectMapper.writeValue(new File(filename), users);
        return true;
    }

    /**
     * Loads Helpers from the JSON file into the set
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        users = new TreeSet<>();

        // Deserializes the JSON objects from the file into a set of Helper Users
        users = objectMapper.readValue(new File(filename), objectMapper.getTypeFactory().constructCollectionType(Set.class, User.class));

        return true;
    }

    @Override
    public Set<Need> getFundingBasket(User user) throws IOException {
        if (user != null) {
            return user.getBasket();
        }
        return null;
    }

    @Override
    public User getUser(String username) throws IOException {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}