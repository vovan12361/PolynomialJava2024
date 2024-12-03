package ru.smak.polynomial.math;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NewtonTest {

    @Test
    void testSinglePoint() {
        ArrayList<Double> x = new ArrayList<>(List.of(1.0));
        ArrayList<Double> y = new ArrayList<>(List.of(2.0));
        Newton newton = new Newton(x, y);
        assertEquals("2.0", newton.toString(), "Polynomial should match constant value");
    }

    @Test
    void testLinearFunction() {
        ArrayList<Double> x = new ArrayList<>(List.of(1.0, 2.0));
        ArrayList<Double> y = new ArrayList<>(List.of(3.0, 5.0));

        Newton newton = new Newton(x, y);
        assertEquals("2.0x+1.0", newton.toString());
        assertEquals(3.0, newton.calc(1.0), 1e-6);
        assertEquals(5.0, newton.calc(2.0), 1e-6);
    }

    @Test
    void testHashMap() {
        HashMap<Double, Double> points = new HashMap<>();
        points.put(1.0, 1.0);
        points.put(2.0, 4.0);
        points.put(3.0, 9.0);
        Newton newton = new Newton(points);
        assertEquals("x^2", newton.toString());
    }

    @Test
    void testAddNode() {
        ArrayList<Double> x = new ArrayList<>(List.of(1.0, 2.0));
        ArrayList<Double> y = new ArrayList<>(List.of(3.0, 5.0));

        Newton newton = new Newton(x, y);

        assertEquals("2.0x+1.0", newton.toString());
        assertEquals(3.0, newton.calc(1.0), 1e-6);
        assertEquals(5.0, newton.calc(2.0), 1e-6);

        newton.addNode(3.0, 11.0);

        assertEquals("2.0x^2-4.0x+5.0", newton.toString());
        assertEquals(3.0, newton.calc(1.0), 1e-6);
        assertEquals(5.0, newton.calc(2.0), 1e-6);
        assertEquals(11.0, newton.calc(3.0), 1e-6);

        HashMap<Double, Double> expectedPoints = new HashMap<>();
        expectedPoints.put(1.0, 3.0);
        expectedPoints.put(2.0, 5.0);
        expectedPoints.put(3.0, 11.0);
        assertEquals(expectedPoints, newton.getPoints());
    }

    @Test
    void secondTestAddNode() {
        ArrayList<Double> x = new ArrayList<>(List.of(1.0, 2.0));
        ArrayList<Double> y = new ArrayList<>(List.of(6.0, 11.0));

        Newton newton = new Newton(x, y);

        assertEquals("5.0x+1.0", newton.toString());

        newton.addNode(3.0, 18.0);

        assertEquals("x^2+2.0x+3.0", newton.toString());
    }


    @Test
    void testDividedDifferences() {
        ArrayList<Double> x = new ArrayList<>(List.of(1.0, 2.0, 3.0));
        ArrayList<Double> y = new ArrayList<>(List.of(6.0, 11.0, 18.0));

        ArrayList<Double> dividedDiff = Newton.calculateDividedDifferences(x, y);
        ArrayList<Double> expectedCoefficients = new ArrayList<>(List.of(6.0, 5.0, 1.0));

       assertEquals(expectedCoefficients, dividedDiff);
    }

    @Test
    void testInvalidInputSize() {
        ArrayList<Double> x = new ArrayList<>(List.of(1.0, 2.0));
        ArrayList<Double> y = new ArrayList<>(List.of(3.0));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Newton(x, y));

        assertEquals("Sizes of x and y must be the same.", exception.getMessage());
    }

    @Test
    void testEdgeCaseWithZeros() {
        ArrayList<Double> x = new ArrayList<>(List.of(0.0, 1.0));
        ArrayList<Double> y = new ArrayList<>(List.of(0.0, 0.0));

        Newton newton = new Newton(x, y);
        assertEquals("0", newton.toString(), "Polynomial should be zero if all y are zero");
    }
}