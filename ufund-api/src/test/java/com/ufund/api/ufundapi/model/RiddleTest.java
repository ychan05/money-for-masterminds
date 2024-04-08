package com.ufund.api.ufundapi.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RiddleTest {

    @Test
    public void testRiddleConstructorWithId() {
        int id = 1;
        String question = "What has keys but can't open locks?";
        String answer = "A piano";
        Riddle riddle = new Riddle(id, question, answer);
        assertEquals(id, riddle.getId());
        assertEquals(question, riddle.getQuestion());
        assertEquals(answer, riddle.getAnswer());
    }

    @Test
    public void testRiddleConstructorWithoutId() {
        String question = "What has a head, a tail, is brown, and has no legs?";
        String answer = "A penny";
        Riddle riddle = new Riddle(question, answer);
        assertEquals(question, riddle.getQuestion());
        assertEquals(answer, riddle.getAnswer());
    }

    @Test
    public void testSettersAndGetters() {
        Riddle riddle = new Riddle(2, "Why did the scarecrow win an award?", "Because he was outstanding in his field.");
        int newId = 3;
        String newQuestion = "What can travel around the world while staying in a corner?";
        String newAnswer = "A stamp";
        riddle.setId(newId);
        riddle.setQuestion(newQuestion);
        riddle.setAnswer(newAnswer);
        assertEquals(newId, riddle.getId());
        assertEquals(newQuestion, riddle.getQuestion());
        assertEquals(newAnswer, riddle.getAnswer());
    }

    @Test
    public void testToString() {
        int id = 4;
        String question = "What is always in front of you but can't be seen?";
        String answer = "The future";
        Riddle riddle = new Riddle(id, question, answer);
        String expectedString = String.format("Riddles [id=%d, question=%s, answer=%s]", id, question, answer);
        assertEquals(expectedString, riddle.toString());
    }
}
