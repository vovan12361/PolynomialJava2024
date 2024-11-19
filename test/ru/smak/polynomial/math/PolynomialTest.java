package ru.smak.polynomial.math;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.cert.PolicyNode;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    @BeforeEach
    void setUp() {
        System.out.println("Starting test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Finished");
    }

    @Test
    void testToString() {
        Polynomial p1 = new Polynomial(0.);
        Assertions.assertEquals("0", p1.toString());
        Polynomial p2 = new Polynomial(0., 1.);
        Assertions.assertEquals("x", p2.toString());

    }

    @Test
    void testTimes() {
        Polynomial p = new Polynomial(1., 0.5, -2., 0.);
        Polynomial p2 = new Polynomial(2., 1., -4., 0.);
        Polynomial pk = p.times(2.);
        Assertions.assertEquals(p2, pk);
    }

    @Test
    void testPlus() {
        Polynomial p = new Polynomial(1., 0.5, -2., 0.);
        Polynomial p2 = new Polynomial(2., 1., -4., 0.);
        Polynomial p3 = new Polynomial(-2., -1., 4.);
        Polynomial p4 = new Polynomial(2.);
        Polynomial pk = p.plus(p2);
        Polynomial pr = new Polynomial(3., 1.5, -6., 0.);
        Polynomial pk2 = p2.plus(p3);
        Polynomial pk3 = p3.plus(p4);
        Polynomial pk4 = p4.plus(p3);
        Polynomial pr2 = new Polynomial(0., -1., 4.);
        Assertions.assertEquals(pr, pk);
        Assertions.assertEquals(new Polynomial(), pk2);
        Assertions.assertEquals(pr2, pk3);
        Assertions.assertEquals(pr2, pk4);
    }

    @Test
    void testGetPower() {
        Polynomial p = new Polynomial();
        Assertions.assertEquals(0, p.getPower());
        Polynomial p1 = new Polynomial(1., 1.);
        Assertions.assertEquals(1, p1.getPower());
        Polynomial p2 = new Polynomial(2., 1., -4., 0.);
        Assertions.assertEquals(2, p2.getPower());
    }

    @Test
    void testTimesPolynomial() {
        Polynomial p = new Polynomial(1., 2.);
        Polynomial p1 = new Polynomial(1., 0., 2.);
        //(2x+1)(2x^2+1)= 4x^3+2x+2x^2+1
        Polynomial tp = new Polynomial(1.,2.,2.,4.);
        Assertions.assertEquals(tp,p.times(p1));
        Polynomial p2 = new Polynomial();
        Assertions.assertEquals(new Polynomial(),p.times(p2));

    }
}