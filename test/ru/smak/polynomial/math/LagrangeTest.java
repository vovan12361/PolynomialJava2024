package ru.smak.polynomial.math;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LagrangeTest {

    @Test
    void testSinglePoint() {
        ArrayList<Double> x = new ArrayList<>(List.of(1.0));
        ArrayList<Double> y = new ArrayList<>(List.of(2.0));

        Lagrange lagrange = new Lagrange(x, y);
        assertEquals("2.0", lagrange.toString());
    }

    @Test
    void testLinearFunction() {
        ArrayList<Double> x = new ArrayList<>(List.of(1.0, 2.0));
        ArrayList<Double> y = new ArrayList<>(List.of(3.0, 5.0));

        Lagrange lagrange = new Lagrange(x, y);
        assertEquals("2.0x+1.0", lagrange.toString());
    }

    @Test
    void testSimple() {
        ArrayList<Double> x = new ArrayList<>(List.of(1.0, 2.0, 3.0, 4.0));
        ArrayList<Double> y = new ArrayList<>(List.of(1.0, 4.0, 9.0, 16.0));

        Lagrange lagrange = new Lagrange(x, y);
        assertEquals(3, lagrange.getPower());
        assertEquals(0, lagrange.getCoeffs().get(0), 1e-9);
        assertEquals(0, lagrange.getCoeffs().get(1), 1e-9);
        assertEquals(1.0, lagrange.getCoeffs().get(2), 1e-9);
    }

    @Test
    void testQuadraticFunction() {
        ArrayList<Double> x = new ArrayList<>(List.of(1.0, 2.0, 3.0));
        ArrayList<Double> y = new ArrayList<>(List.of(6.0, 11.0, 18.0));

        Lagrange lagrange = new Lagrange(x, y);
        assertEquals("x^2+2.0x+3.0", lagrange.toString());
    }

    @Test
    void testInvalidInputSize() {
        ArrayList<Double> x = new ArrayList<>(List.of(1.0, 2.0));
        ArrayList<Double> y = new ArrayList<>(List.of(3.0));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Lagrange(x, y));

        assertEquals("Sizes of x and y must be the same.", exception.getMessage());
    }

    @Test
    void testCalculateValue() {
        ArrayList<Double> x = new ArrayList<>(List.of(1.0, 2.0, 3.0));
        ArrayList<Double> y = new ArrayList<>(List.of(2.0, 3.0, 5.0));

        Lagrange lagrange = new Lagrange(x, y);
        assertEquals(2.0, lagrange.calc(1.0), 1e-6);
        assertEquals(3.0, lagrange.calc(2.0), 1e-6);
        assertEquals(5.0, lagrange.calc(3.0), 1e-6);
    }

    @Test
    void testEdgeCaseWithZeros() {
        ArrayList<Double> x = new ArrayList<>(List.of(0.0, 1.0));
        ArrayList<Double> y = new ArrayList<>(List.of(0.0, 0.0));

        Lagrange lagrange = new Lagrange(x, y);
        assertEquals("0", lagrange.toString());
    }
}
