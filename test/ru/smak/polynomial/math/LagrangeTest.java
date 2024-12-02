package ru.smak.polynomial.math;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LagrangeTest {

    @Test
    void testSinglePoint() {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        x.add(1.0);
        y.add(2.0);

        Lagrange lagrange = new Lagrange(x, y);
        assertEquals("2.0", lagrange.toString(), "Polynomial should match constant value");
    }

    @Test
    void testLinearFunction() {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        x.add(1.0);
        x.add(2.0);
        y.add(3.0);
        y.add(5.0);

        Lagrange lagrange = new Lagrange(x, y);
        assertEquals("2.0x+1.0", lagrange.toString(), "Polynomial should represent linear function");
    }

    @Test
    void testQuadraticFunction() {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        x.add(1.0);
        x.add(2.0);
        x.add(3.0);
        y.add(6.0);
        y.add(11.0);
        y.add(18.0);

        Lagrange lagrange = new Lagrange(x, y);
        assertEquals("x^2+2.0x+3.0", lagrange.toString(), "Polynomial should represent quadratic function");
    }

    @Test
    void testInvalidInputSize() {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        x.add(1.0);
        x.add(2.0);
        y.add(3.0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Lagrange(x, y));

        assertEquals("Sizes of x and y must be the same.", exception.getMessage());
    }

    @Test
    void testCalculateValue() {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        x.add(1.0);
        x.add(2.0);
        x.add(3.0);
        y.add(2.0);
        y.add(3.0);
        y.add(5.0);

        Lagrange lagrange = new Lagrange(x, y);
        assertEquals(2.0, lagrange.calc(1.0), 1e-6, "Lagrange polynomial should match y at x=1");
        assertEquals(3.0, lagrange.calc(2.0), 1e-6, "Lagrange polynomial should match y at x=2");
        assertEquals(5.0, lagrange.calc(3.0), 1e-6, "Lagrange polynomial should match y at x=3");
    }

    @Test
    void testEdgeCaseWithZeros() {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        x.add(0.0);
        x.add(1.0);
        y.add(0.0);
        y.add(0.0);

        Lagrange lagrange = new Lagrange(x, y);
        assertEquals("0", lagrange.toString(), "Polynomial should be zero if all y are zero");
    }
}
