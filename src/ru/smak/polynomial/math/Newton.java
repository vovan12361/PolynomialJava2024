package ru.smak.polynomial.math;

import java.util.ArrayList;
import java.util.HashMap;

public class Newton extends Polynomial {
    // Точки в словарь map hashmap
    /*
    private final ArrayList<Double> coefficients;
    private final ArrayList<Double> nodes;
    */
    private final HashMap<Double, Double> points;
    private static Polynomial multiplier;

    public Newton(ArrayList<Double> x, ArrayList<Double> y) {
        super(calculateNewton(x, y));
        this.points = new HashMap<>();
        for(int i = 0; i < x.size(); i++) {
            points.put(x.get(i), y.get(i));
        }
    }

    public Newton(HashMap<Double, Double> constructPoints) {
        super(calculateNewton(constructPoints));
        this.points = constructPoints;
    }

    /**
     * Вычисляет разделённые разности.
     *
     * @param x - список узлов (x)
     * @param y - значения функции в узлах (x)
     */
    protected static ArrayList<Double> calculateDividedDifferences(ArrayList<Double> x, ArrayList<Double> y) {
        int n = x.size();
        ArrayList<Double> diff = new ArrayList<>(y);
        for (int i = 1; i < n; i++) {
            for (int j = n - 1; j >= i; j--) {
                diff.set(j, (diff.get(j) - diff.get(j - 1)) / (x.get(j) - x.get(j - i)));
            }
        }
        return diff;
    }

    /**
     * Вычисляет полином Ньютона.
     *
     * @param constructPoints - список точек (x, y)
     */
    private static ArrayList<Double> calculateNewton(HashMap<Double, Double> constructPoints) {
        ArrayList<Double> x = new ArrayList<>(constructPoints.keySet());
        ArrayList<Double> y = new ArrayList<>(constructPoints.values());
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("Sizes of x and y must be the same.");
        }
        ArrayList<Double> dividedDiff = calculateDividedDifferences(x, y);
        Polynomial result = new Polynomial(0.0);
        multiplier = new Polynomial(1.0);

        for (int i = 0; i < dividedDiff.size(); i++) {
            Polynomial term = multiplier.times(dividedDiff.get(i));
            result = result.plus(term);
            if (i < x.size() - 1) {
                multiplier = multiplier.times(new Polynomial(-x.get(i), 1.0));
            }
        }

        return result.getCoeffs();
    }

    private static ArrayList<Double> calculateNewton(ArrayList<Double> x, ArrayList<Double> y) {
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("Sizes of x and y must be the same.");
        }
        ArrayList<Double> dividedDiff = calculateDividedDifferences(x, y);
        Polynomial result = new Polynomial(0.0);
        multiplier = new Polynomial(1.0);

        for (int i = 0; i < dividedDiff.size(); i++) {
            Polynomial term = multiplier.times(dividedDiff.get(i));
            result = result.plus(term);
            if (i < x.size() - 1) {
                multiplier = multiplier.times(new Polynomial(-x.get(i), 1.0));
            }
        }

        return result.getCoeffs();
    }

    /**
     * Добавляет новый узел в полином без пересчета всех коэффициентов.
     *
     * @param xNew новый узел (x).
     * @param yNew значение функции в новом узле (y).
     */
    public void addNode(double xNew, double yNew) {
        double prod = 1.0;
        for (double x : points.keySet()) {
            prod *= (xNew - x);
        }

        double newCoefficient = (yNew - this.calc(xNew)) / prod;
        points.put(xNew, yNew);

        Polynomial newTerm = new Polynomial(newCoefficient);
        for(double x: points.keySet()) {
            if(x != xNew) newTerm = newTerm.times(new Polynomial(-x, 1.0));
        }
        Polynomial updatedPolynomial = new Polynomial(this.getCoeffs());
        updatedPolynomial = updatedPolynomial.plus(newTerm);
        super.setCoeffs(updatedPolynomial.getCoeffs());
    }

    /**
     * Возвращает пары точек в виде словаря (x, y)
     */
    public HashMap<Double, Double> getPoints() {
        return points;
    }


}
