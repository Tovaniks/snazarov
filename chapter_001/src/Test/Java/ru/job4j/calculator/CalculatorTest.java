package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenSevenMinusOneThenSix() {
        Calculator calc = new Calculator();
        calc.substract(7D, 1D);
        double result = calc.getResult();
        double expected = 6;
        assertThat(result, is(expected));
    }

    @Test
    public void whenEigthDivTwoThenFour() {
        Calculator calc = new Calculator();
        calc.div(8D, 2D);
        double result = calc.getResult();
        double expected = 4;
        assertThat(result, is(expected));
    }

    @Test
    public void whenTwoMultiplyThreeThenSix() {
        Calculator calc = new Calculator();
        calc.multiple(2D, 3D);
        double result = calc.getResult();
        double expected = 6;
        assertThat(result, is(expected));
    }
}