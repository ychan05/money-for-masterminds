package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

/**
 * Implements the functionality for JSON peristence for Authenticator
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Graden Olson
 */

@Component
public class AuthenticatorFileDAO implements AuthenticatorDAO{
    private static final Logger LOG = Logger.getLogger(AuthenticatorFileDAO.class.getName());
    Map<String, User> users;    // Provides a local cache of the user objects
    private ObjectMapper objectMapper;  // Provides conversion between User objects and JSON text format
    private String filename;    // Filename to read from and write to

    public AuthenticatorFileDAO(@Value("${authenticator.file}") String filename, ObjectMapper objectMapper) throws IOException{
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
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

    private boolean save() throws IOException {
        User[] userArray = getUsersArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),userArray);
        return true;
    }

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

    // TODO - ADD PASSWORD
    @Override
    public User signin(User user) throws IOException {
        synchronized(users){
            // Create a new user object
            if(!users.containsKey(user.getUsername())){
                users.put(user.getUsername(), user);
                save(); // may throw IO Exception
                return user;
            }
            else return null; // returns null if username already exists
        }
    }

    // TODO - ADD PASSWORD
    @Override
    public User login(String username) throws IOException {
        synchronized(users){
            if(users.containsKey(username)){
                return users.get(username);
            }
            else return null; // returns null if user does not exist
        }
    }
}
