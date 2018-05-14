package com.kata.romanNumbertransform;

import com.jlt.kata.romanNumbertransform.RomanNumbersToArabicNumbersTransForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RomanNumbersToArabicNumbersTransFormTest {

    private RomanNumbersToArabicNumbersTransForm transForm = new RomanNumbersToArabicNumbersTransForm();
    private String expected;
    private int target;

    public RomanNumbersToArabicNumbersTransFormTest(int target, String expected) {
        this.expected = expected;
        this.target = target;
    }

    @Parameters
    public static Collection suites() {
        return Arrays.asList(new Object[][]{
                {1, "I"},
                {2, "II"},
                {3, "III"},
                {4, "IV"},
                {5, "V"},
                {6, "VI"},
                {7, "VII"},
                {8, "VIII"},
                {9, "IX"},
                {10, "X"},
                {11, "XI"},
                {12, "XII"},
                {15, "XV"},
                {19, "XIX"},
                {20, "XX"},
                {40, "XL"},
                {50, "L"},
                {100, "C"},
                {500, "D"},
                {900, "CM"},
                {1000, "M"},
                {3000, "MMM"}
        });
    }

    @Test
    public void testConvert() {
        assertEquals(expected, transForm.convert(target));
    }

}
