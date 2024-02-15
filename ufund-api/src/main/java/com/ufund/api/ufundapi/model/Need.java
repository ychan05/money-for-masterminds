package com.ufund.api.ufundapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

/*  
 * Represents a Need entity
 * 
 * @author Graden Olson
*/
public class Need {
    private static final Logger LOG = Logger.getLogger(Need.class.getName());

    static final String STRING_FORMAT = "Need [name=%s, price=%f, quantity=%d]";

    @JsonProperty("name") private String name;
    @JsonProperty("price") private double price;
    @JsonProperty("quantity") private int quantity;

    /**
     * Create a Need with the given name, price and quantity
     * @param name The name of the Need
     * @param price The price of the Need
     * @param quantity The quantity of the Need
     */
    public Need(@JsonProperty("name") String name, @JsonProperty("price") double price, @JsonProperty("quantity") int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Retrieves the name of the Need
     * @return The name of the Need
     */
    public String getName() {return name;}

    /**
     * Sets the name of the Need
     * @param name The name of the Need
     */
    public void setName(String name) {this.name = name;}

    /**
     * Retrieves the price of the Need
     * @return The price of the Need
     */
    public double getPrice() {return price;}

    /**
     * Sets the price of the Need
     * @param price The price of the Need
     */
    public void setPrice(double price) {this.price = price;}

    /**
     * Retrieves the quantity of the Need
     * @return The quantity of the Need
     */
    public int getQuantity(int quantity) {return quantity;}

    /**
     * Sets the quantity of the Need
     * @param quantity
     */
    public void setQuantity(int quantity) {this.quantity = quantity;}

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return String.format(STRING_FORMAT, name, price, quantity);
    }
}
