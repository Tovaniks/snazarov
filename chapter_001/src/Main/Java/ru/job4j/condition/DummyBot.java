package ru.job4j.condition;

/**
 * @author Sergey Nazarov (tovaniks@yandex.ru)
 * @version $Id$
 * @since 2018.01.30
 */
public class DummyBot {
    /**
     * Отвечает на вопросы.
     *
     * @param question Вопрос от клиента.
     * @return Ответ.
     */
    public String answer(String question) {
        String answer = "Это ставит меня в тупик. Спросите другой вопрос.";
        if ("Привет, Бот.".equals(question)) {
            answer = "Привет, умник.";
        } else if ("Пока.".equals(question)) {
            answer = "До скорой встречи.";
        }
        return answer;
    }
}