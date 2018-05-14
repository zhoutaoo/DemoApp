package com.kata.guessNumeric;

import com.jlt.kata.guessNumeric.Referee;
import com.jlt.kata.guessNumeric.Result;
import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RefereeTest {
    @Test
    public void CalculateResult_0A0B() {
        Referee referee = new Referee(4);
        Result result = referee.CalculateResult("1234", "5678");
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("0A0B,Fail", result.getResultMessage());
    }

    @Test
    public void CalculateResult_0A4B() {
        Referee referee = new Referee(4);
        Result result = referee.CalculateResult("1234", "4321");
        assertFalse(result.isSuccess());
        Assert.assertEquals("0A4B,Fail", result.getResultMessage());
    }

    @Test
    public void CalculateResult_2A2B() {
        Referee referee = new Referee(4);
        Result result = referee.CalculateResult("1258", "1285");
        assertFalse(result.isSuccess());
        Assert.assertEquals("2A2B,Fail", result.getResultMessage());
    }

    @Test
    public void CalculateResult_4A0B() {
        Referee referee = new Referee(4);
        Result result = referee.CalculateResult("1258", "1258");
        assertTrue(result.isSuccess());
        Assert.assertEquals("4A0B,Guess Success", result.getResultMessage());
    }

    @Test
    public void isInvalidInput_false() {
        Referee referee = new Referee(4);
        assertFalse(referee.isInvalidInputString("1258"));
    }

    @Test
    public void isInvalidInput_true_one_digits_duplicate() {
        Referee referee = new Referee(4);
        assertTrue(referee.isInvalidInputString("2258"));
    }

    @Test
    public void isInvalidInput_true_all_digits_duplicate() {
        Referee referee = new Referee(4);
        assertTrue(referee.isInvalidInputString("2222"));
    }

    @Test
    public void isInvalidInput_true_long() {
        Referee referee = new Referee(4);
        assertTrue(referee.isInvalidInputString("22222"));
    }

    @Test
    public void isInvalidInput_true_short() {
        Referee referee = new Referee(4);
        assertTrue(referee.isInvalidInputString("123"));
    }

    @Test
    public void isInvalidInput_true_empty() {
        Referee referee = new Referee(4);
        assertTrue(referee.isInvalidInputString(""));
    }

    @Test
    public void isInvalidInput_true_null() {
        Referee referee = new Referee(4);
        assertTrue(referee.isInvalidInputString(null));
    }

  /*  @Test
    public void isContainsDuplicateNumeric_false_1234() throws Exception {
        Referee referee = new Referee(4);
        Method method = referee.getClass().getDeclaredMethod("isContainsDuplicateNumeric", String.class);
        method.setAccessible(true);
        boolean isContainsDuplicateNumeric = (boolean) method.invoke(referee, "1234");
        assertFalse(isContainsDuplicateNumeric);
    }

    @Test
    public void isContainsDuplicateNumeric_false_1224() throws Exception {
        Referee referee = new Referee(4);
        Method method = referee.getClass().getDeclaredMethod("isContainsDuplicateNumeric", String.class);
        method.setAccessible(true);
        boolean isContainsDuplicateNumeric = (boolean) method.invoke(referee, "1224");
        assertTrue(isContainsDuplicateNumeric);
    }*/
}
