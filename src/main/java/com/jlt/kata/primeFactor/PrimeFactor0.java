package com.jlt.kata.primeFactor;

import java.util.*;

public class PrimeFactor0 {
    public String decomposition(int i) {
        List<Integer> result = new ArrayList();
        int a = 2;
        while (a <= i) {
            while (i % a == 0) {
                result.add(a);
                i = i / a;
            }
            a++;
        }
        return getString(result);
    }

    private String getString(List<Integer> result) {
        String resultStr = "";
        for (int a : result)
            resultStr = resultStr + " " + a;
        return resultStr.trim();
    }
}
