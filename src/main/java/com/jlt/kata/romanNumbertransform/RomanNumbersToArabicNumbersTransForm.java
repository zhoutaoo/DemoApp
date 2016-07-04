package com.jlt.kata.romanNumbertransform;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class RomanNumbersToArabicNumbersTransForm {
    private SortedMap<Integer, String> romanNumbers = new TreeMap<Integer, String>(Collections.reverseOrder()) {{
        put(1000, "M");
        put(900, "CM");
        put(500, "D");
        put(400, "CD");
        put(100, "C");
        put(90, "XC");
        put(50, "L");
        put(40, "XL");
        put(10, "X");
        put(9, "IX");
        put(5, "V");
        put(4, "IV");
        put(1, "I");
    }};

    public String convert(int number) {
        if (number == 0)
            return "";
        int key = romanNumbers.tailMap(number).firstKey();
        return romanNumbers.get(key) + convert(number - key);
       /* int i = 0;
        while (its.hasNext()) {
            i = (int) its.next();
            return romanNumbers.get(i) + convert(number - i);
        }*/
        ///**/ return "";
    }
}
