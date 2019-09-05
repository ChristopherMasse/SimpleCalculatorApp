package com.christophermasse.simplecalculator.service;

/**
 * Basic operations to be performed by a calculator
 */

public interface BasicOperations {

    double subtract(double a, double b);

    double add(double a, double b);

    double multiply(double a, double b);

    double divide(double a, double b) throws ArithmeticException;
}
