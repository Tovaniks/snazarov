package ru.job4j.tracker;

/**
 * ConsoleInput
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.22
 */


public class StubInput implements Input {

    private final String[] answers;

    private int position;


    public StubInput(final String[] value) {
        this.answers = value;
    }

    /**
     * Возвращаем ответ из массива(списка) с ответами
     *
     * @param question запрос
     * @return ответ.
     */
    public String ask(String question) {
        return this.answers[this.position++];
    }
}
