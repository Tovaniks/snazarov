package ru.job4j.search;

import java.util.HashMap;
import java.util.List;

/**
 * User.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.15
 */
public class UserConvert {


    /**
     * Конвертируем список пользователей в структуру HashMap<Ключ, Пользователь>
     *
     * @param list список пользователей
     * @return HashMap<Ключ ,   Пользователь>
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getID(), user);
        }
        return result;
    }


}