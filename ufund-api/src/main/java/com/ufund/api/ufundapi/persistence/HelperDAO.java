package com.ufund.api.ufundapi.persistence;

import java.io.IOException;
import java.util.Set;
import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.model.Need;

/**
 * Defines the interface for Helper User object persistence
 * @author Benjamin Hemmers
 */

public interface HelperDAO {
    /**
     * Retrieves the funding basket of the specified Helper User
     * 
     * @param user The Helper User
     * @return A Set of Needs representing the funding basket of the Helper User
     * @throws IOException if an issue with underlying storage
     */
    Set<Need> getFundingBasket(User user) throws IOException;

    /**
     * Retrieves user by username
     * @param username The username of the user
     * @return The user object
     * @throws IOException if an issue with underlying storage
     */
    User getUser(String username) throws IOException;
}