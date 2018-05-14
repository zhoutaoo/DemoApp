package com.kata.guessNumeric;

import org.junit.Assert;
import org.junit.Test;
import com.jlt.kata.guessNumeric.Result;

public class ResultTest {
    @Test
    public void isSuccess_false() {
        Result result = new Result(1, 3);
        Assert.assertFalse(result.isSuccess());
    }

    @Test
    public void isSuccess_true() {
        Result result = new Result(4, 0);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void getResultCode_4A0B() {
        Result result = new Result(4, 0);
        Assert.assertEquals("4A0B,Guess Success", result.getResultMessage());
    }

    @Test
    public void getResultCode_1A3B() {
        Result result = new Result(1, 3);
        Assert.assertEquals("1A3B,Fail", result.getResultMessage());
    }

    @Test
    public void getResultCode_0A4B() {
        Result result = new Result(0, 4);
        Assert.assertEquals("0A4B,Fail", result.getResultMessage());
    }

    @Test
    public void getResultCode_2A2B() {
        Result result = new Result(2, 2);
        Assert.assertEquals("2A2B,Fail", result.getResultMessage());
    }

    @Test
    public void getResultCode_0A0B() {
        Result result = new Result(0, 0);
        Assert.assertEquals("0A0B,Fail", result.getResultMessage());
    }
}
