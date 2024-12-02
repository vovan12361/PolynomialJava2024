package ru.smak.polynomial.math;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NewtonTest {

    @Test
    void testSinglePoint() {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        x.add(1.0);
        y.add(2.0);

        Newton newton = new Newton(x, y);
        assertEquals("2.0", newton.toString(), "Polynomial should match constant value");
    }

    @Test
    void testLinearFunction() {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        x.add(1.0);
        x.add(2.0);
        y.add(3.0);
        y.add(5.0);

        Newton newton = new Newton(x, y);
        assertEquals("2.0x+1.0", newton.toString(), "Polynomial should represent linear function");
        assertEquals(3.0, newton.calc(1.0), 1e-6, "Polynomial should match y=3 at x=1");
        assertEquals(5.0, newton.calc(2.0), 1e-6, "Polynomial should match y=5 at x=2");
    }

    @Test
    void testAddNode() {
        // Начальные узлы
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        x.add(1.0);
        x.add(2.0);
        y.add(3.0);
        y.add(5.0);

        // Создаём начальный полином Ньютона
        Newton newton = new Newton(x, y);

        // Проверяем исходный полином
        assertEquals("2.0x+1.0", newton.toString(), "Initial polynomial should represent linear function");
        assertEquals(3.0, newton.calc(1.0), 1e-6, "Initial polynomial should match y=3 at x=1");
        assertEquals(5.0, newton.calc(2.0), 1e-6, "Initial polynomial should match y=5 at x=2");

        // Добавляем новый узел
        newton.addNode(3.0, 11.0);

        // Проверяем обновлённый полином
        assertEquals("2.0x^2-4.0x+5.0", newton.toString(), "Updated polynomial should represent quadratic function");
        assertEquals(3.0, newton.calc(1.0), 1e-6, "Updated polynomial should still match y=3 at x=1");
        assertEquals(5.0, newton.calc(2.0), 1e-6, "Updated polynomial should still match y=5 at x=2");
        assertEquals(11.0, newton.calc(3.0), 1e-6, "Updated polynomial should match y=11 at x=3");

        // Проверяем разделённые разности
        ArrayList<Double> expectedCoefficients = new ArrayList<>();
        expectedCoefficients.add(3.0);  // f[x0]
        expectedCoefficients.add(2.0);  // f[x1, x0]
        expectedCoefficients.add(2.0);  // f[x2, x1, x0]
        assertEquals(expectedCoefficients, newton.getCoefficients(), "Divided differences should be updated correctly");
    }

    @Test
    void secondTestAddNode() {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        x.add(1.0);
        x.add(2.0);
        y.add(6.0);
        y.add(11.0);

        Newton newton = new Newton(x, y);
        newton.addNode(3, 18);
        assertEquals("x^2+2.0x+3.0", newton.toString(), "Polynomial should represent quadratic function");
    }

    @Test
    void testDividedDifferences() {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        x.add(1.0);
        x.add(2.0);
        x.add(3.0);
        y.add(6.0);
        y.add(11.0);
        y.add(18.0);

        Newton newton = new Newton(x, y);
        ArrayList<Double> expectedCoefficients = new ArrayList<>();
        expectedCoefficients.add(6.0);  // f[x0]
        expectedCoefficients.add(5.0);  // f[x1, x0]
        expectedCoefficients.add(1.0);  // f[x2, x1, x0]

        assertEquals(expectedCoefficients, newton.getCoefficients(), "Divided differences should match expected values");
    }

    @Test
    void testInvalidInputSize() {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        x.add(1.0);
        x.add(2.0);
        y.add(3.0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Newton(x, y));

        assertEquals("Sizes of x and y must be the same.", exception.getMessage());
    }

    @Test
    void testEdgeCaseWithZeros() {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        x.add(0.0);
        x.add(1.0);
        y.add(0.0);
        y.add(0.0);

        Newton newton = new Newton(x, y);
        assertEquals("0", newton.toString(), "Polynomial should be zero if all y are zero");
    }
}