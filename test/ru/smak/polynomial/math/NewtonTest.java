package ru.smak.polynomial.math;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        assertEquals("2.0x+1.0", newton.toString(), "Polynomial should represent linear function");
        assertEquals(3.0, newton.calc(1.0), 1e-6, "Polynomial should match y=3 at x=1");
        assertEquals(5.0, newton.calc(2.0), 1e-6, "Polynomial should match y=5 at x=2");
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
        assertEquals(11.0, newton.calc(3.0), 1e-63);

        ArrayList<Double> expectedCoefficients = new ArrayList<>();
        expectedCoefficients.add(3.0);  // f[x0]
        expectedCoefficients.add(2.0);  // f[x1, x0]
        expectedCoefficients.add(2.0);  // f[x2, x1, x0]
        assertEquals(expectedCoefficients, newton.getCoefficients());
    }

    @Test
    void secondTestAddNode() {
        ArrayList<Double> x = new ArrayList<>(List.of(1.0, 2.0));
        ArrayList<Double> y = new ArrayList<>(List.of(6.0, 11.0));

        Newton newton = new Newton(x, y);
        newton.addNode(3, 18);
        assertEquals("x^2+2.0x+3.0", newton.toString(), "Polynomial should represent quadratic function");
    }

    @Test
    void testDividedDifferences() {
        ArrayList<Double> x = new ArrayList<>(List.of(1.0, 2.0, 3.0));
        ArrayList<Double> y = new ArrayList<>(List.of(6.0, 11.0, 18.0));

        Newton newton = new Newton(x, y);
        ArrayList<Double> expectedCoefficients = new ArrayList<>();
        expectedCoefficients.add(6.0);  // f[x0]
        expectedCoefficients.add(5.0);  // f[x1, x0]
        expectedCoefficients.add(1.0);  // f[x2, x1, x0]

        assertEquals(expectedCoefficients, newton.getCoefficients());
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