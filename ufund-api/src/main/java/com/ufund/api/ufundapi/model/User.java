package com.ufund.api.ufundapi.model;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

/*  
 * Represents a User entity
 * 
 * @author Graden Olson
*/
public class User {
    private static final Logger LOG = Logger.getLogger(User.class.getName());

    static final String STRING_FORMAT = "User [username=%s]";

    @JsonProperty("username") private String username;
    @JsonProperty("basket") private Set<Need> basket;

    /**
     * Create a User with the given username, and an empty funding basket
     * @param username The username of the User
     */
    public User(@JsonProperty("username") String username){
        this.username = username;
        basket = new HashSet<>();
    }

    /**
     * Retrieves the username of the User
     * @return the username of the User
     */
    public String getUsername() {return username;}

    /**
     * Sets the username of the User
     * @param username The username of the user
     */
    public void setUsername(String username) {this.username = username;}

    /**
     * Retrieves the funding basket of the User
     * @return The funding basket of the User
     */
    public Set<Need> getBasket() {return basket;}

    /**
     * Adds a need to the funding basket
     * @param need The need being added to the funding basket
     */
    public void addToBasket(Need need){basket.add(need);}
}
