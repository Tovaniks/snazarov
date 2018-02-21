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
}
