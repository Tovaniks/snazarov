package ru.job4j.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.test.action.Ask;
import ru.job4j.test.action.Bid;
import ru.job4j.test.type.Add;
import ru.job4j.test.type.Delete;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TraidTest {

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
    public void whenAddTwoTaskThenReturnTrue() {
        Traid traid = new Traid();
        assertThat(traid.add(new Task("1", "gazprom", new Add(), new Bid(), 10d, 5)), is(true));
        assertThat(traid.add(new Task("2", "gazprom", new Add(), new Bid(), 15d, 6)), is(true));
        traid.getGlass("gazprom");
        assertThat(this.mem.toString(),
                is(String.format("Sell\tPrice\tBuy%s%s%s%s%s%s", System.getProperty("line.separator"),
                        "\t\t15.0\t6", System.getProperty("line.separator"), "\t\t10.0\t5", System.getProperty("line.separator"), System.getProperty("line.separator"))));
    }

    @Test
    public void whenAddDuplicateByBookIDPriceTaskThenFalse() {
        Traid traid = new Traid();
        assertThat(traid.add(new Task("1", "gazprom", new Add(), new Bid(), 10d, 5)), is(true));
        assertThat(traid.add(new Task("1", "gazprom", new Add(), new Bid(), 15d, 6)), is(false));
        assertThat(traid.add(new Task("2", "gazprom", new Add(), new Bid(), 10d, 5)), is(true));
        assertThat(traid.add(new Task("1", "yandex", new Add(), new Bid(), 15d, 6)), is(true));
        assertThat(traid.add(new Task("1", "yandex", new Delete(), new Bid(), 15d, 6)), is(true));
        assertThat(traid.add(new Task("1", "yandex", new Delete(), new Bid(), 15d, 6)), is(false));
    }

    @Test
    public void whenAddSamePriceThenVolumeAdded() {
        Traid traid = new Traid();
        assertThat(traid.add(new Task("1", "gazprom", new Add(), new Ask(), 10d, 5)), is(true));
        assertThat(traid.add(new Task("2", "gazprom", new Add(), new Ask(), 10d, 6)), is(true));
        traid.getGlass("gazprom");
        assertThat(this.mem.toString(),
                is(String.format("Sell\tPrice\tBuy%s%s%s%s", System.getProperty("line.separator"),
                        "11\t10.0\t", System.getProperty("line.separator"), System.getProperty("line.separator"))));
    }


    @Test
    public void whenDeleteTaskThenDeleted() {
        Traid traid = new Traid();
        assertThat(traid.add(new Task("1", "gazprom", new Add(), new Ask(), 10d, 5)), is(true));
        assertThat(traid.add(new Task("2", "gazprom", new Add(), new Ask(), 10d, 6)), is(true));
        assertThat(traid.add(new Task("2", "gazprom", new Delete(), new Ask(), 10d, 6)), is(true));
        traid.getGlass("gazprom");
        assertThat(this.mem.toString(),
                is(String.format("Sell\tPrice\tBuy%s%s%s%s", System.getProperty("line.separator"),
                        "5\t10.0\t", System.getProperty("line.separator"), System.getProperty("line.separator"))));
    }


    @Test
    public void whenInsertAddAndDeleteTask() {
        Traid traid = new Traid();
        traid.add(new Task("1", "gazprom", new Add(), new Bid(), 10d, 5));
        traid.add(new Task("2", "gazprom", new Add(), new Bid(), 15d, 6));
        traid.add(new Task("3", "gazprom", new Add(), new Ask(), 50d, 6));
        traid.getGlass("gazprom");
        assertThat(this.mem.toString(),
                is(String.format("Sell\tPrice\tBuy%s%s%s%s%s%s%s%s", System.getProperty("line.separator"),
                        "6\t50.0\t", System.getProperty("line.separator"),
                        "\t\t15.0\t6", System.getProperty("line.separator"),
                        "\t\t10.0\t5", System.getProperty("line.separator"), System.getProperty("line.separator"))));
    }
}