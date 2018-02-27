package ru.job4j.tracker;

import java.util.Scanner;

/**
 * ConsoleInput
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.21
 */

public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Получаем ответ от пользователя
     *
     * @param question запрос
     * @return ответ пользователя.
     */
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
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
            throw new MenuOutException(String.format("Введите правильное ID пункта меню. r %s не существует", String.valueOf(key)));
        }
        return key;
    }
}
