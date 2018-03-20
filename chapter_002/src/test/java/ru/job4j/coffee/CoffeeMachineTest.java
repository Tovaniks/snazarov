package ru.job4j.coffee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {

    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;


    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenPayThenChange() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.validChange(43, 17);
        int[] expect = {10, 10, 5, 1};
        for (int index = 0; index < result.length; index++) {
            assertThat(result[index], is(expect[index]));
        }
    }

    @Test
    public void whenPayEqualPriceThenNull() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.validChange(15, 15);
        assertThat(result, is(nullValue()));
    }

    @Test
    public void whenPayLessThenZeroThenException() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.validChange(0, -17);
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Цена/сумма не корректны%n", System.lineSeparator())
                )
        );
    }
}
