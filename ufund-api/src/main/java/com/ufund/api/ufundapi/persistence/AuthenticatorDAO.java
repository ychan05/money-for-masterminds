package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import com.ufund.api.ufundapi.model.User;
 /**
  * Defines the interface for User object persistence
  * @author Graden Olson
  */

public interface AuthenticatorDAO {
    /**
     * If username is a User, allows access to service
     * @param username The username of the person logging in
     * @return User that logged in
     * @throws IOException if issue with underlying storage
     */
    User login(String username, String password) throws IOException;

    /**
     * If username is not already a User, creates new account
     * @param username The username of the person signing in
     * @return User created
     * @throws IOException if issue with underlying storage
     */
    User signin(User user) throws IOException;
}
