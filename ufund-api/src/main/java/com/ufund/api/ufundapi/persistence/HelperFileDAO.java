package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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
    Map<String, User> users;    // Provides a local cache of the user objects
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

    private User[] getUsersArray() {
        return getUsersArray(null);
    }

    private User[] getUsersArray(String containsText) { // if containsText == null, no filter
        ArrayList<User> userArrayList = new ArrayList<>();

        for (User user : users.values()) {
            if (containsText == null || user.getUsername().contains(containsText)) {
                userArrayList.add(user);
            }
        }

        User[] userArray = new User[userArrayList.size()];
        userArrayList.toArray(userArray);
        return userArray;
    }

    /**
     * Saves the Helper Users into the file as an array of JSON objects
     * 
     * @return true if the Helpers were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        User[] userArray = getUsersArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),userArray);
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
        users = new TreeMap<>();

        // Deserializes the JSON objects from the file into an array of users
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        User[] userArray = objectMapper.readValue(new File(filename),User[].class);

        // Add each user to the tree map
        for (User user : userArray) {
            users.put(user.getUsername(), user);
        }

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
        for (User user : users.values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void addNeedToBasket(User user, Need need) throws IOException {
        if (user != null) {
            user.addToBasket(need);
            save();
        }
    }

    @Override
    public void removeNeedFromBasket(User user, Need need) throws IOException {
        if (user != null) {
            user.removeFromBasket(need);
            save();
        }
    }

    @Override
    public Need getNeedFromBasket(User user, int needId) throws IOException {
        if (user != null) {
            for (Need need : user.getBasket()) {
                if (need.getId() == needId) {
                    return need;
                }
            }
        }
        return null;
    }
}