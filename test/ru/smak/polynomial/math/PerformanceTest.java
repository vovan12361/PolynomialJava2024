package ru.smak.polynomial.math;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class PerformanceTest {
    @Test
    void testPerformanceLagrangeVsNewton() {
        final int numPoints = 1000;
        Random random = new Random();

        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        for (int i = 0; i < numPoints; i++) {
            x.add((double) i);
            y.add(random.nextDouble() * 100);
        }

        // Тестируем полином Лагранжа
        long lagrangeStart = System.currentTimeMillis();
        Lagrange lagrange = new Lagrange(x, y);
        lagrange.calc(500.0);
        long lagrangeEnd = System.currentTimeMillis();
        long lagrangeTime = lagrangeEnd - lagrangeStart;
        System.out.println("Lagrange time: " + lagrangeTime + " ms");

        // Тестируем полином Ньютона
        long newtonStart = System.currentTimeMillis();
        Newton newton = new Newton(x, y);
        newton.calc(500.0);
        long newtonEnd = System.currentTimeMillis();
        long newtonTime = newtonEnd - newtonStart;
        System.out.println("Newton time: " + newtonTime + " ms");

        System.out.println("Newton is " + lagrangeTime/newtonTime + " times faster than Lagrange ");
    }
}
