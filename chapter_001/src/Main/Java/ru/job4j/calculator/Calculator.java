package ru.job4j.calculator;


/**
 * Class Calculate.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.01.28
 */


public class Calculator {


    private double result;

    /**
     * add.
     *
     * @param first, second - summarize the numbers
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * substract.
     *
     * @param first, second - defference between nubbers
     */
    public void substract(double first, double second) {
        this.result = first - second;
    }


    /**
     * div.
     *
     * @param first, second - divide the first number by the second
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * multiple.
     *
     * @param first, second - multiply the numbers
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * getResult
     * return result.
     */
    public double getResult() {
        return this.result;
    }


}