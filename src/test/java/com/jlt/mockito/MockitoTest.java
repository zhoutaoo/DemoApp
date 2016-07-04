package com.jlt.mockito;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

;

/**
 * Created by zhoutao on 14-3-6.
 */
public class MockitoTest {
    @Test
    public void mockito_test1() {
        ArrayList list = mock(ArrayList.class);
        when(list.get(0)).thenReturn("test");
        when(list.get(2)).thenThrow(new RuntimeException());

        Assert.assertEquals("test", list.get(0));
        Assert.assertEquals(null, list.get(1));
        Assert.assertNull(list.get(1));
    }
    @Test
    public void mockito_test2() {
        ArrayList list = mock(ArrayList.class);
        when(list.get(anyInt())).thenReturn("element");
        list.add("once");
        verify(list).add("once");
        verify(list, times(1)).add("once");
    }
}
