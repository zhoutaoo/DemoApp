package com.jlt.kata.primeFactor;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactor2 {
    public List<Integer> decomposition(int number) {
        List<Integer> result = new ArrayList<Integer>();
        int i = 2;
        while (i <= number) {
            while (number % i == 0) {
                number /= i;
                result.add(i);
            }
            i++;
        }
        return result;
    }
}
