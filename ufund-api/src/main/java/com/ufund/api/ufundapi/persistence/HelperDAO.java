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

    /**
     * Adds a need to the funding basket of the specified Helper User
     * 
     * @param user The Helper User
     * @param need The Need to add to the funding basket
     * @throws IOException if an issue with underlying storage
     */
    void addNeedToBasket(User user, Need need) throws IOException;

    /**
     * Removes a need from the funding basket of the specified Helper User
     * 
     * @param user The Helper User
     * @param need The Need to remove from the funding basket
     * @throws IOException if an issue with underlying storage
     */
    void removeNeedFromBasket(User user, Need need) throws IOException;

    /**
     * Retrieve need from the funding basket of the specified Helper User
     * @param user The Helper User
     * @param needId The id of the need to retrieve
     * @return The need object
     * @throws IOException if an issue with underlying storage
     */
    Need getNeedFromBasket(User user, int needId) throws IOException;

    /**
     * Checkouts needs from the funding basket of the specified Helper User
     *  @param user The Helper User
     * @throws IOException if an issue with underlying storage
     * 
     * IMPORTANT: This method should be called after the cupboard has been updated
     */
    void checkoutNeeds(User user) throws IOException;
}