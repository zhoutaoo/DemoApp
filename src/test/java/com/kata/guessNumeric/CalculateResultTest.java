package com.kata.guessNumeric;/*
package com.game.guessNumeric;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class CalculateResultTest {
    @Test
    public void countOfCorrect_0() throws Exception {
        CalculateResult calculateResult = new CalculateResult();
        Method method = calculateResult.getClass().getDeclaredMethod("countOfCorrect", String.class, String.class);
        method.setAccessible(true);
        int count = (int) method.invoke(calculateResult, "1234", "5423");
        assertNotNull(count);
        assertEquals(0, count);
    }

    @Test
    public void countOfCorrect_4() throws Exception {
        CalculateResult calculateResult = new CalculateResult();
        Method method = calculateResult.getClass().getDeclaredMethod("countOfCorrect", String.class, String.class);
        method.setAccessible(true);
        int count = (int) method.invoke(calculateResult, "1234", "1234");
        assertNotNull(count);
        assertEquals(4, count);
    }

    @Test
    public void countOfCorrect_2() throws Exception {
        CalculateResult calculateResult = new CalculateResult();
        Method method = calculateResult.getClass().getDeclaredMethod("countOfCorrect", String.class, String.class);
        method.setAccessible(true);
        int count = (int) method.invoke(calculateResult, "1234", "1258");
        assertNotNull(count);
        assertEquals(2, count);
    }

    @Test
    public void countOfDigitsCorrect_3() throws Exception {
        CalculateResult calculateResult = new CalculateResult();
        Method method = calculateResult.getClass().getDeclaredMethod("countOfDigitsCorrect", String.class, String.class);
        method.setAccessible(true);
        int count = (int) method.invoke(calculateResult, "1234", "5432");
        assertNotNull(count);
        assertEquals(3, count);
    }

    @Test
    public void countOfDigitsCorrect_4() throws Exception {
        CalculateResult calculateResult = new CalculateResult();
        Method method = calculateResult.getClass().getDeclaredMethod("countOfDigitsCorrect", String.class, String.class);
        method.setAccessible(true);
        int count = (int) method.invoke(calculateResult, "1234", "1234");
        assertNotNull(count);
        assertEquals(4, count);
    }

    @Test
    public void countOfDigitsCorrect_0() throws Exception {
        CalculateResult calculateResult = new CalculateResult();
        Method method = calculateResult.getClass().getDeclaredMethod("countOfDigitsCorrect", String.class, String.class);
        method.setAccessible(true);
        int count = (int) method.invoke(calculateResult, "1234", "5678");
        assertNotNull(count);
        assertEquals(0, count);
    }
}
*/
