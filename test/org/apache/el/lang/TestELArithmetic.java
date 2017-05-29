package org.apache.el.lang;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: org.apache.el.lang
 * Author:  Novemser
 * 2017/5/29
 */
public class TestELArithmetic extends TestCase {
    private static final String a = "1.1";
    private static final BigInteger b =
            new BigInteger("1000000000000000000000");

    @Test
    public void testAdd01() throws Exception {
        assertEquals("1000000000000000000001.1",
                String.valueOf(ELArithmetic.add(a, b)));
    }

    @Test
    public void testSubtract01() throws Exception {
        assertEquals("-999999999999999999998.9",
                String.valueOf(ELArithmetic.subtract(a, b)));
    }

    @Test
    public void testMultiply01() throws Exception {
        assertEquals("1100000000000000000000.0",
                String.valueOf(ELArithmetic.multiply(a, b)));
    }

    @Test
    public void testDivide01() throws Exception {
        assertEquals("0.0",
                String.valueOf(ELArithmetic.divide(a, b)));
    }


    @Test
    public void testMod01() throws Exception {
        assertEquals("1.1",
                String.valueOf(ELArithmetic.mod(a, b)));
    }

    @Test
    public void testBug47371bigDecimal() throws Exception {
        assertEquals(BigDecimal.valueOf(1),
                ELArithmetic.add("", BigDecimal.valueOf(1)));
    }

    @Test
    public void testBug47371double() throws Exception {
        assertEquals(Double.valueOf(7), ELArithmetic.add("", Double.valueOf(7)));
    }

    @Test
    public void testBug47371doubleString() throws Exception {
        assertEquals(Double.valueOf(2), ELArithmetic.add("", "2."));
    }

    @Test
    public void testBug47371bigInteger() throws Exception {
        assertEquals(BigInteger.valueOf(0),
                ELArithmetic.multiply("", BigInteger.valueOf(1)));
    }

    @Test
    public void testBug47371long() throws Exception {
        assertEquals(Long.valueOf(1), ELArithmetic.add("", Integer.valueOf(1)));
    }

    @Test
    public void testBug47371long2() throws Exception {
        assertEquals(Long.valueOf(-3), ELArithmetic.subtract("1", "4"));
    }

    @Test
    public void testBug47371doubleString2() throws Exception {
        assertEquals(Double.valueOf(2), ELArithmetic.add("1.", "1"));
    }
}
