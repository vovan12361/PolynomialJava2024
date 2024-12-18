package ru.smak.polynomial.math;

import java.util.ArrayList;

public class Lagrange extends Polynomial {

    private final ArrayList<Double> xValues; // Разделённые разности
    private final ArrayList<Double> yValues; // Узловые точки

    public Lagrange(ArrayList<Double> x, ArrayList<Double> y) {
        super(calculateLagrange(x, y));
        this.xValues = x;
        this.yValues = y;
    }

    public void addNode(double x, double y) {
        xValues.add(x);
        yValues.add(y);
        calculateLagrange(xValues, yValues);
    }

    private static ArrayList<Double> calculateLagrange(ArrayList<Double> x, ArrayList<Double> y) {
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("Sizes of x and y must be the same.");
        }

        int n = x.size();
        Polynomial result = new Polynomial(0.0);
        for (int i = 0; i < n; i++) {
            Polynomial li = new Polynomial(1.0);
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double denom = x.get(i) - x.get(j);
                    Polynomial term = new Polynomial(-x.get(j), 1.0);
                    li = li.times(term.div(denom));
                }
            }
            result = result.plus(li.times(y.get(i)));
        }
        return result.getCoeffs();
    }
}
