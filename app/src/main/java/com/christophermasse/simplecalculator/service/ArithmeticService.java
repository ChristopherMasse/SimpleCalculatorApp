package com.christophermasse.simplecalculator.service;

/**
 * Implementation of the basic arithmetic operations
 */
public class ArithmeticService implements BasicOperations {

    @Override
    public double subtract(double a, double b) {
        return a - b;
    }

    @Override
    public double add(double a, double b) {
        return a + b;
    }

    @Override
    public double multiply(double a, double b) {
        return a*b;
    }

    @Override
    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0d) throw new ArithmeticException("Cannot divide by zero");
        return a/b;
    }
}
