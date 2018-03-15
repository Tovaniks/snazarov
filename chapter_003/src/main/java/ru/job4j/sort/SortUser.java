package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * AddItem
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.25
 */
public class SortUser {

    /**
     * Сортируем пользователей в списке
     *
     * @param list список пользователей
     * @return отсортированный список пользователей
     */
    public Set<User> sort(List<User> list) {
        Set<User> result = new TreeSet<>();
        for (User user : list) {
            result.add(user);
        }
        return result;
    }
}