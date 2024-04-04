package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.Riddle;

public class RiddleFileDAO implements RiddleDAO {
    private static final Logger LOG = Logger.getLogger(CupboardFileDAO.class.getName());
    Map<Integer,Riddle> riddles;   // Provides a local cache of the riddle objects
                                // so that we don't need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between Riddle
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new Riddle
    private String filename;    // Filename to read from and write to

    /**
     * Creates a Riddle File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public RiddleFileDAO(@Value("${riddle.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the cupboard from the file
    }

    /**
     * Generates the next id for a new {@linkplain Riddle riddle}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain Riddle riddles} from the tree map
     * 
     * @return  The array of {@link Riddle iddles}, may be empty
     */
    private Riddle[] getRiddlesArray() {
        return getRiddlesArray(null);
    }

    /**
     * Generates an array of {@linkplain Riddle riddles} from the tree map for any
     * {@linkplain Riddle riddles} that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the {@linkplain Riddle riddles}
     * in the tree map
     * 
     * @return  The array of {@link Riddle riddles}, may be empty
     */
    private Riddle[] getRiddlesArray(String containsText) { // if containsText == null, no filter
        ArrayList<Riddle> riddleArrayList = new ArrayList<>();

        for (Riddle riddle : riddles.values()) {
            if (containsText == null || riddle.getQuestion().contains(containsText)) {
                riddleArrayList.add(riddle);
            }
        }

        Riddle[] riddleArray = new Riddle[riddleArrayList.size()];
        riddleArrayList.toArray(riddleArray);
        return riddleArray;
    }

    /**
     * Saves the {@linkplain Riddle riddles} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link Riddle riddles} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Riddle[] riddleArray = getRiddlesArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),riddleArray);
        return true;
    }

   /**
     * Loads {@linkplain Riddle riddles} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        riddles = new TreeMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of needs
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Riddle[] riddleArray = objectMapper.readValue(new File(filename),Riddle[].class);

        // Add each need to the tree map and keep track of the greatest id
        for (Riddle riddle : riddleArray) {
            riddles.put(riddle.getId(),riddle);
            if (riddle.getId() > nextId)
                nextId = riddle.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteRiddle(int id) throws IOException {
        synchronized(riddles) {
            if (riddles.containsKey(id)) {
                riddles.remove(id);
                return save();
            } else {
                return false;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Riddle createRiddle(Riddle riddle) throws IOException {
        synchronized(riddles){
            // Create a new need object with the next unique id
            Riddle newRiddle = new Riddle(nextId(), riddle.getQuestion(), riddle.getAnswer());
            
            riddles.put(newRiddle.getId(), newRiddle);
            save(); // may throw an IOException
            return newRiddle;
        }
    }

   /**
     * {@inheritDoc}
     */ 
    @Override
    public Riddle updateRiddle(Riddle riddle) throws IOException {
        synchronized(riddles){
            if (!riddles.containsKey(riddle.getId())){
                return null;
            }
            riddles.put(riddle.getId(), riddle);
            save();
            return riddle;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Riddle[] findRiddles(String containsText) {
        synchronized(riddles){
            return getRiddlesArray(containsText);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Riddle getRiddle(int id) throws IOException {
        synchronized(riddles) {
            if (!riddles.containsKey(id)) {
                return null;
            }
            return riddles.get(id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Riddle[] getRiddles() throws IOException {
        synchronized(riddles){
            ArrayList<Riddle> riddleArrayList = new ArrayList<>();

            for (Riddle riddle : riddles.values()) {
                riddleArrayList.add(riddle);
            }
    
            Riddle[] riddleArray = new Riddle[riddleArrayList.size()];
            riddleArrayList.toArray(riddleArray);
            return riddleArray;
        }
    }
}
