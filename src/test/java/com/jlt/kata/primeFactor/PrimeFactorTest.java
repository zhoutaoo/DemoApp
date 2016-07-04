package com.jlt.kata.primeFactor;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: zhoutao
 * Date: 14-2-19
 * Time: 上午10:09
 * To change this template use File | Settings | File Templates.
 */
public class PrimeFactorTest {
    @Test
    public void must_return_1_give_empty() {
        Assert.assertEquals("", new PrimeFactor().decomposition(1));
    }

    @Test
    public void must_return_2_give_2() {
        Assert.assertEquals("2", new PrimeFactor().decomposition(2));
    }

    @Test
    public void must_return_3_give_3() {
        Assert.assertEquals("3", new PrimeFactor().decomposition(3));
    }

    @Test
    public void must_return_2_2_give_4() {
        Assert.assertEquals("2 2", new PrimeFactor().decomposition(4));
    }

    @Test
    public void must_return_5_give_5() {
        Assert.assertEquals("5", new PrimeFactor().decomposition(5));
    }

    @Test
    public void must_return_2_3_give_6() {
        Assert.assertEquals("2 3", new PrimeFactor().decomposition(6));
    }

    @Test
    public void must_return_7_give_7() {
        Assert.assertEquals("7", new PrimeFactor().decomposition(7));
    }

    @Test
    public void must_return_8_give_8() {
        Assert.assertEquals("2 2 2", new PrimeFactor().decomposition(8));
    }

    @Test
    public void must_return_3_3_give_9() {
        Assert.assertEquals("3 3", new PrimeFactor().decomposition(9));
    }

    @Test
    public void must_return_2_5_give_10() {
        Assert.assertEquals("2 5", new PrimeFactor().decomposition(10));
    }

    @Test
    public void must_return_2_3_3_give_18() {
        Assert.assertEquals("2 3 3", new PrimeFactor().decomposition(18));
    }

    @Test
    public void must_return_5_5_give_25() {
        Assert.assertEquals("5 5", new PrimeFactor().decomposition(25));
    }

    @Test
    public void must_return_3_3_7_give_63() {
        Assert.assertEquals("3 3 7", new PrimeFactor().decomposition(63));
    }

    @Test
    public void must_return_19_19_give_361() {
        Assert.assertEquals("19 19", new PrimeFactor().decomposition(361));
    }

    @Test
    public void must_return_65537_give_65537() {
        Assert.assertEquals("65537", new PrimeFactor().decomposition(65537));
    }
}
