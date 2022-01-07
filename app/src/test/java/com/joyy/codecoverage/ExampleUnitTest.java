package com.joyy.codecoverage;

import static org.junit.Assert.*;

import org.junit.Test;

//import com.joyy.aardemo.util.MathCalc;
import com.joyy.codecoverage.util.MathCalc;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testAddFunc() {
        assertEquals(3, MathCalc.add(1, 2));
    }
    @Test
    public void testMultiplyFunc() {
        assertEquals(2, MathCalc.multiply(1, 2));
    }
    @Test
    public void testDivideFunc() {
        assertEquals(0.5, MathCalc.divide(1, 2), 0);
    }
}