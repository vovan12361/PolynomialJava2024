package ru.smak.polynomial.math;

import java.util.ArrayList;

public class Newton extends Polynomial {
    private final ArrayList<Double> coefficients; // Разделённые разности
    private final ArrayList<Double> nodes; // Узловые точки

    public Newton(ArrayList<Double> x, ArrayList<Double> y) {
        super(calculateNewton(x, y));
        this.nodes = x;
        this.coefficients = calculateDividedDifferences(x, y);
    }

    /**
     * Вычисляет разделённые разности.
     *
     * @param x - список узлов x
     * @param y - список значений в узлах x
     */
    private static ArrayList<Double> calculateDividedDifferences(ArrayList<Double> x, ArrayList<Double> y) {
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
     * @param x - список узлов (x)
     * @param y - значения функции в узлах (x)
     */
    private static ArrayList<Double> calculateNewton(ArrayList<Double> x, ArrayList<Double> y) {
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("Sizes of x and y must be the same.");
        }
        ArrayList<Double> dividedDiff = calculateDividedDifferences(x, y);
        Polynomial result = new Polynomial(0.0); // Начальный полином
        Polynomial multiplier = new Polynomial(1.0); // Множители (x - x0)(x - x1) ...

        for (int i = 0; i < dividedDiff.size(); i++) {
            Polynomial term = multiplier.times(dividedDiff.get(i)); // a_i * (x - x0)(x - x1)...
            result = result.plus(term);
            if (i < x.size() - 1) {
                multiplier = multiplier.times(new Polynomial(-x.get(i), 1.0)); // (x - xi)
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
        // Вычисляем новое значение для разделённой разности
        double prod = 1.0; // Произведение (xNew - x_i) для всех существующих x_i
        for (double x : nodes) {
            prod *= (xNew - x);
        }

        // Вычисляем коэффициент для нового узла
        double newCoefficient = (yNew - this.calc(xNew)) / prod;

        // Добавляем новый коэффициент
        coefficients.add(newCoefficient);

        // Обновляем список узлов
        nodes.add(xNew);

        // Обновляем полином с учётом нового члена
        Polynomial updatedPolynomial = new Polynomial(0.0);
        for (int i = 0; i < coefficients.size(); i++) {
            Polynomial term = new Polynomial(coefficients.get(i));
            for (int j = 0; j < i; j++) {
                term = term.times(new Polynomial(-nodes.get(j), 1.0)); // (x - xj)
            }
            updatedPolynomial = updatedPolynomial.plus(term);
        }

        // Обновляем коэффициенты полинома
        super.setCoeffs(updatedPolynomial.getCoeffs());
    }

    /**
     * Возвращает коэффициенты (разделённые разности).
     */
    public ArrayList<Double> getCoefficients() {
        return new ArrayList<>(coefficients);
    }

    /**
     * Возвращает узловые точки.
     */
    public ArrayList<Double> getNodes() {
        return new ArrayList<>(nodes);
    }
}
