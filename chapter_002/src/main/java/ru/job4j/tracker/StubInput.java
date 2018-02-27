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

    /**
     * Получаем ответ от пользователя
     *
     * @param question запрос
     * @param range диапазон пунктов меню
     * @return ID пункта меню
     */
    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exists = false;
        for (int value : range) {
            if (value == key) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            throw new MenuOutException(String.format("Введите правильное ID пункта меню. %s не существует", key));
        }
        return key;
    }
}
