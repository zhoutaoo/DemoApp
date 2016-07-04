package com.jlt.kata.primeFactor;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhoutao
 * Date: 14-2-19
 * Time: 上午10:09
 * To change this template use File | Settings | File Templates.
 */
public class PrimeFactor1Test {

    private PrimeFactor1 primeFactor1;
    private List<Integer> result;

    @Before
    public void before() {
        primeFactor1 = new PrimeFactor1();
        result = new ArrayList<>();
    }

    @Test
    public void must_return_empty_when_give_1() {
        Assert.assertEquals(result, primeFactor1.decomposition(1));
    }

    @Test
    public void must_return_2_when_give_2() {
        result.add(2);
        Assert.assertEquals(result, primeFactor1.decomposition(2));
    }

    @Test
    public void must_return_3_when_give_3() {
        result.add(3);
        Assert.assertEquals(result, primeFactor1.decomposition(3));
    }

    @Test
    public void must_return_4_when_give_4() {
        result.add(2);
        result.add(2);
        Assert.assertEquals(result, primeFactor1.decomposition(4));
    }

    @Test
    public void must_return_5_when_give_5() {
        result.add(5);
        Assert.assertEquals(result, primeFactor1.decomposition(5));
    }

    @Test
    public void must_return_6_when_give_6() {
        result.add(5);
        Assert.assertEquals(result, primeFactor1.decomposition(5));
    }

    @Test
    public void must_return_7_when_give_7() {
        result.add(7);
        Assert.assertEquals(result, primeFactor1.decomposition(7));
    }

    @Test
    public void must_return_8_when_give_8() {
        result.add(2);
        result.add(2);
        result.add(2);
        Assert.assertEquals(result, primeFactor1.decomposition(8));
    }

    @Test
    public void must_return_18_when_give_18() {
        result.add(2);
        result.add(3);
        result.add(3);
        Assert.assertEquals(result, primeFactor1.decomposition(18));
    }

    @Test
    public void must_return_27_when_give_27() {
        result.add(3);
        result.add(3);
        result.add(3);
        Assert.assertEquals(result, primeFactor1.decomposition(27));
    }

    @Test
    public void must_return_29_when_give_29() {
        result.add(29);
        Assert.assertEquals(result, primeFactor1.decomposition(29));
    }
}
