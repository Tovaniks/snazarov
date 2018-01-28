package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.01.28
 */

public class CalculatorTest {
    /**
     * Test method Add
     */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Test method substract
     */
    @Test
    public void whenSevenMinusOneThenSix() {
        Calculator calc = new Calculator();
        calc.substract(7D, 1D);
        double result = calc.getResult();
        double expected = 6;
        assertThat(result, is(expected));
    }

    /**
     * Test method div
     */
    @Test
    public void whenEigthDivTwoThenFour() {
        Calculator calc = new Calculator();
        calc.div(8D, 2D);
        double result = calc.getResult();
        double expected = 4;
        assertThat(result, is(expected));
    }

    /**
     * Test method multiple
     */
    @Test
    public void whenTwoMultiplyThreeThenSix() {
        Calculator calc = new Calculator();
        calc.multiple(2D, 3D);
        double result = calc.getResult();
        double expected = 6;
        assertThat(result, is(expected));
    }
}