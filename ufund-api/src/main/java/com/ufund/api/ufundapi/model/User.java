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
    @JsonProperty("password") private String password;

    /**
     * Create a User with the given username, and an empty funding basket
     * @param username The username of the User
     */
    public User(@JsonProperty("username") String username, @JsonProperty("password") String password){
        this.username = username;
        this.password = password;
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
         * Retrieves the password of the User
         * @return the password of the User
         */
    public String getPassword() {return password;}

    /**
         * Sets the password of the User
         * @param username The password of the user
         */
        public void setPassword(String password) {this.password = password;}

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

    /**
     * Removes a need from the funding basket
     * @param need The need being removed from the funding basket
     */
    public void removeFromBasket(Need need){basket.remove(need);}

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, username);
    }
}
