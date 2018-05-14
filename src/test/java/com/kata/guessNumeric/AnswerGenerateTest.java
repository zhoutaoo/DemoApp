package com.kata.guessNumeric;

import com.jlt.kata.guessNumeric.AnswerGenerate;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class AnswerGenerateTest {
    @Test
    public void generate4DigitsAnswer_not_duplicate() {
        AnswerGenerate answerGenerate = new AnswerGenerate();
        String answer = answerGenerate.generateAnswer(4);
        assertNotNull(answer);
        assertEquals(4, answer.length());
        for (int i = 0; i < answer.length(); i++) {
            if (answer.substring(i + 1).indexOf(answer.charAt(i)) > -1)
                fail("answer contains duplicate num!");
        }
    }

    @Test
    public void returnNotContainsDigitString_original() throws Exception {
        AnswerGenerate answerGenerate = new AnswerGenerate();
        Method method = answerGenerate.getClass().getDeclaredMethod("returnNotContainsDigitString", String.class, char.class);
        method.setAccessible(true);
        String digit = (String) method.invoke(answerGenerate, "1234", '5');
        assertNotNull(digit);
        assertEquals("5", digit);
    }

    @Test
    public void returnNotContainsDigitString_empty() throws Exception {
        AnswerGenerate answerGenerate = new AnswerGenerate();
        Method method = answerGenerate.getClass().getDeclaredMethod("returnNotContainsDigitString", String.class, char.class);
        method.setAccessible(true);
        String digit = (String) method.invoke(answerGenerate, "1234", '3');
        assertNotNull(digit);
        assertEquals("", digit);
    }

    @Test
    public void testGenerateDigits_success() throws Exception {
       /* AnswerGenerate answerGenerate = new AnswerGenerate();
        Method method = answerGenerate.getClass().getDeclaredMethod("generateDigits");
        method.setAccessible(true);
        char answer = (char) method.invoke(answerGenerate);
        assertNotNull(answer);
        assertTrue(answer >= '0');
        assertTrue(answer <= '9');*/
    }
} 
