package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model-tier")
public class RiddleTest {
    @Test
    public void testCtor() {
        // setup
        int expected_id = 99;
        String expected_question = "What weighs six ounces, sits in a tree, and is very dangerous?";
        String expected_answer = "A sparrow with a machine gun.";

        // invoke
        Riddle riddle = new Riddle(expected_id, expected_question, expected_answer);

        // analyze
        assertEquals(expected_id, riddle.getId());
        assertEquals(expected_question, riddle.getQuestion());
        assertEquals(expected_answer, riddle.getAnswer());
    }

    @Test
    public void testQuestion() {
        // setup
        int id = 99;
        String question = "What weighs six ounces, sits in a tree, and is very dangerous?";
        String answer = "A sparrow with a machine gun.";
        Riddle riddle = new Riddle(id, question, answer);

        String expected_question = "When you do not know what I am, I am something, but when you know what I am, I am nothing. What am I?";

        // invoke
        riddle.setQuestion(expected_question);

        // analyze
        assertEquals(expected_question, riddle.getQuestion());
    }

    @Test
    public void testAnswer() {
        // setup
        int id = 99;
        String question = "What weighs six ounces, sits in a tree, and is very dangerous?";
        String answer = "A sparrow with a machine gun.";
        Riddle riddle = new Riddle(id, question, answer);

        String expected_answer = "A riddle.";

        // invoke
        riddle.setAnswer(expected_answer);

        // analyze
        assertEquals(expected_answer, riddle.getAnswer());

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

    @Test
    public void testToString() {
        // setup
        int id = 99;
        String question = "What weighs six ounces, sits in a tree, and is very dangerous?";
        String answer = "A sparrow with a machine gun.";
        String expected_string = String.format(Riddle.STRING_FORMAT, id, question, answer);
        Riddle riddle = new Riddle(id, question, answer);

        // invoke
        String actual_string = riddle.toString();

        // analyze
        assertEquals(expected_string, actual_string);
    }
}
