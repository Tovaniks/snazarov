package ru.job4j.tracker;

/**
 * ValidateInput
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.27
 */
public class ValidateInput extends ConsoleInput {

    /**
     * Получаем ответ от пользователя
     *
     * @param question запрос
     * @param range диапазон пунктов меню
     * @return ID пункта меню
     */
    @Override
    public int ask(String question, int[] range) {
        int key;
        do {
            try {
                key = super.ask(question, range);
                break;
            } catch (MenuOutException moe) {
                for (int i : range) {
                    System.out.println(i);
                }
                System.out.println("Введите правильное ID пункта меню");
            } catch (NumberFormatException nfe) {
                System.out.println("Пожалуйста, введите виладные данные");
            }
        } while (true);
        return key;
    }
}
