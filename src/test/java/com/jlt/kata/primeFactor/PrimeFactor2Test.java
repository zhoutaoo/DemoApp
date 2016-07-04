package com.jlt.kata.primeFactor;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhoutao
 * Date: 14-2-19
 * Time: 上午10:09
 * To change this template use File | Settings | File Templates.
 */
public class PrimeFactor2Test {

    private PrimeFactor2 primeFactor2;

    @Before
    public void before() {
        primeFactor2 = new PrimeFactor2();
    }

    @Test
    public void must_return_empty_when_give_1() {
        Assert.assertEquals(Arrays.asList(), primeFactor2.decomposition(1));
    }

    @Test
    public void must_return_2_when_give_2() {
        Assert.assertEquals(Arrays.asList(2), primeFactor2.decomposition(2));
    }

    @Test
    public void must_return_3_when_give_3() {
        Assert.assertEquals(Arrays.asList(3), primeFactor2.decomposition(3));
    }

    @Test
    public void must_return_2_2_when_give_4() {
        Assert.assertEquals(Arrays.asList(2,2), primeFactor2.decomposition(4));
    }

    @Test
    public void must_return_5_when_give_5() {
        Assert.assertEquals(Arrays.asList(5), primeFactor2.decomposition(5));
    }

    @Test
    public void must_return_2_3_when_give_6() {
        Assert.assertEquals(Arrays.asList(2,3), primeFactor2.decomposition(6));
    }

    @Test
    public void must_return_2_2_2_when_give_8() {
        Assert.assertEquals(Arrays.asList(2,2,2), primeFactor2.decomposition(8));
    }

    @Test
    public void must_return_3_3_when_give_9() {
        Assert.assertEquals(Arrays.asList(3,3), primeFactor2.decomposition(9));
    }

    @Test
    public void must_return_2_5_when_give_10() {
        Assert.assertEquals(Arrays.asList(2,5), primeFactor2.decomposition(10));
    }

    @Test
    public void must_return_2_3_2_when_give_12() {
        Assert.assertEquals(Arrays.asList(2,2,3), primeFactor2.decomposition(12));
    }
}
