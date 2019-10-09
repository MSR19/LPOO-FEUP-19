package com.aor.refactoring.example3;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleOrderTest {

    @Test
    public void testFixedDiscount() {
        Discount order = new Discount(10);
        assertEquals(90, order.applyDiscount(100), 0.1);
    }

    @Test
    public void testPercentageDiscount() {
        Discount order = new Discount(0.2);
        assertEquals(80, order.applyDiscount(100), 0.1);
    }

    @Test
    public void testNoDiscount() {
        Discount order = new Discount(0.0);
        assertEquals(100, order.applyDiscount(100), 0.1);
    }
}