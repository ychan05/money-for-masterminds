package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import com.ufund.api.ufundapi.model.Riddle;

public interface RiddleDAO {
    /**
     * Retrieves a {@linkplain Riddle riddle} with the given id
     * 
     * @param id The id of the {@link Riddle riddle to get}
     * @return a {@link Riddle riddle} object with the matching id
     * null if no {@link Riddle riddle} with a matching id is found
     * 
     */
    Riddle getRiddle(int id) throws IOException;

    /**
     * Retrieves all {@linkplain Riddle riddles}
     * @return An array of {@link Riddle riddle} objects, may be empty
     */
    Riddle[] getRiddles() throws IOException;

    /**
     * Creates and saves a {@linkplain Riddle riddle}
     * @param riddle {@linkplain}
     * @return
     */
    Riddle createRiddle(Riddle riddle) throws IOException;

    /**
     * Updates and saves a {@linkplain Riddle riddle}
     * @param riddle object to be updated and saved
     * @return updated {@link Riddle riddle} if successful, null if {@link Riddle riddle} could not be found
     */
    public Riddle updateRiddle(Riddle riddle) throws IOException;

    /**
     * Deletes a {@linkplain Riddle riddle} with the given id
     * @param id The id of the {@link Riddle riddle}
     * @return true if {@link Riddle riddle} was deleted
     * or false if riddle with id doesn't exist
     */
    boolean deleteRiddle(int id) throws IOException;

    /**
     * Finds all {@linkplain Riddle riddles} whose question contains the given text
     * @param contrainsText
     * @return
     */
    Riddle[] findRiddles(String containsText);

    
}
