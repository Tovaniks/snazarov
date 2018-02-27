package ru.job4j.tracker;

/**
 * ValidateInput
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.27
 */
public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    /**
     * Получаем ответ от пользователя
     *
     * @param question запрос
     * @return ответ пользователя.
     */
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Получаем ответ от пользователя
     *
     * @param question запрос
     * @param range    диапазон пунктов меню
     * @return ID пункта меню
     */
    @Override
    public int ask(String question, int[] range) {
        int key;
        do {
            try {
                key = this.input.ask(question, range);
                break;
            } catch (MenuOutException moe) {
                System.out.println("Введите правильное ID пункта меню");
            } catch (NumberFormatException nfe) {
                System.out.println("Пожалуйста, введите виладные данные");
            }
        } while (true);
        return key;
    }
}
