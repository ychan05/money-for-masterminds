package com.ufund.api.ufundapi.persistence;

import java.io.IOException;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;
 /**
  * Defines the interface for Need object persistence
  * @author Graden Olson
  */
public interface CupboardDAO {
    /**
     * Retrieves all {@linkplain Need needs}
     * @return An array of {@link Need need} objects, may be empty
     * @throws IOException if an issue with underlying storage
     */
    Need[] getNeeds() throws IOException;

    /**
     * Finds all {@linkplain Need needs} whose name contains the given text
     * @param contrainsText
     * @return
     * @throws IOException
     */
    Need[] findNeeds(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain Need need} with the given id
     * 
     * @param id The id of the {@link Need need to get}
     * @return a {@link Need need} object with the matching id
     * null if no {@link Need need} with a matching id is found
     * 
     * @throws IOException
     */
    Need getNeed(int id) throws IOException;

    /**
     * Creates and saves a {@linkplain Need need}
     * @param hero {@linkplain}
     * @return
     * @throws IOException
     */
    Need createNeed(Need hero) throws IOException;

    /**
     * Updates and saves a {@linkplain Need need}
     * @param need object to be updated and saved
     * @return updated {@link Need need} if successful, null if {@link Need need} could not be found
     * 
     * @throws IOException
     */
    Need updateNeed(Need need) throws IOException;

    /**
     * Deletes a {@linkplain Need need} with the given id
     * @param id The id of the {@link Need need}
     * @return true if {@link Need need} was deleted
     * or false if need with id doesn't exist
     * @throws IOException
     */
    boolean deleteNeed(int id) throws IOException;

    /**
     * Adjust needs according to a user checkout
     * @param user The user who is checking out
     * @throws IOException
     */
    void checkoutNeeds(User user) throws IOException;
}
