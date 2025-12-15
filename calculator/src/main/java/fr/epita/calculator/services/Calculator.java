package fr.epita.calculator.services;

public class Calculator {


    public double divide(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException();
        }
        return (double) numerator / denominator;

    }
}
