package com.ufund.api.ufundapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Riddle {
    private static final Logger LOG = Logger.getLogger(Riddle.class.getName());
    static final String STRING_FORMAT = "Riddles [id=%d, question=%s, answer=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("question") private String question;
    @JsonProperty("answer") private String answer;

    /**
     * Create a Riddle with the given question and answer
     * @param id
     * @param question
     * @param answer
     */
    public Riddle(@JsonProperty("id") int id, @JsonProperty("question") String question, @JsonProperty("answer") String answer){
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public Riddle(@JsonProperty("question") String question, @JsonProperty("answer") String answer){
        this.question = question;
        this.answer = answer;
    }


    /**
     * Retrieves the id of the Riddle
     * @return The id of the Riddle
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets the id of the Riddle
     * @param id The id of the Riddle
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Retrieves the question of the Riddle
     * @return The question of the Riddle
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the question of the Riddle
     * @param question The question of the Riddle
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the answer of the Riddle
     * @param answer The answer of the Riddle
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Sets the question of the Riddle
     * @param question The question of the Riddle
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Returns a string representation of the Riddle
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, question, answer);
    }

}
