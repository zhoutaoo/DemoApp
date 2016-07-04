package com.jlt.jdk.map;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import static junit.framework.Assert.*;

public class SortedMapTest {
    private SortedMap<Integer, String> romanNumbers = new TreeMap<Integer, String>(Collections.reverseOrder()) {{
        put(1, "I");
        put(5, "V");
        put(10, "X");
        put(50, "L");
        put(100, "C");
        put(500, "D");
        put(1000, "M");
    }};

    @Test
    public void testFirstKey_should_be_1000_when_get_firstKey() {
        assertEquals(1000, (int) romanNumbers.firstKey());
    }

    @Test
    public void testLastKey_should_be_1000_when_get_lastKey() {
        assertEquals(1, (int) romanNumbers.lastKey());
    }

    @Test
    public void testTailMap() {
        SortedMap<Integer, String> actual = romanNumbers.tailMap(1);
        assertEquals(1, actual.size());
        assertEquals(1, (int) actual.firstKey());
        assertEquals(1, (int) actual.lastKey());
    }

    @Test
    public void testTailMap_50() {
        SortedMap<Integer, String> actual = romanNumbers.tailMap(50);
        assertEquals(4, actual.size());
        assertEquals(50, (int) actual.firstKey());
        assertEquals(1, (int) actual.lastKey());
    }

    @Test
    public void testHeadMap_50() {
        SortedMap<Integer, String> actual = romanNumbers.headMap(50);
        assertEquals(3, actual.size());
        assertEquals(1000, (int) actual.firstKey());
        assertEquals(100, (int) actual.lastKey());
    }
}
