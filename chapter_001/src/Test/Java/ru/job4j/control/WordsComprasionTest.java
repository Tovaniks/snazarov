package ru.job4j.control;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WordsComprasionTest {

    @Test
    public void whenWordsIsMatched() {
        boolean result = new WordsComprasion().constains("Добрый вечер!", "ый веч");
        assertThat(result, is(true));

    }

    @Test
    public void whenWordsIsFullMatched() {
        boolean result = new WordsComprasion().constains("Как же так", "Как же так");
        assertThat(result, is(true));

    }

    @Test
    public void whenWordsIsNotMatched() {
        boolean result = new WordsComprasion().constains("И я тут был", "ыло");
        assertThat(result, is(false));
    }

}
