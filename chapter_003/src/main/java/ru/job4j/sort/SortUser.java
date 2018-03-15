package ru.job4j.sort;

import java.util.Comparator;
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
        result.addAll(list);
        return result;
    }

    /**
     * Сортируем пользователей в списке по длине имени
     *
     * @param list список пользователей
     * @return отсортированный список пользователей
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User left, User right) {
                return Integer.compare(left.getName().length(), right.getName().length());
            }
        });
        return list;
    }

    /**
     * Сортируем пользователей в списке по имени/возрасту
     *
     * @param list список пользователей
     * @return отсортированный список пользователей
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User left, User right) {
                return left.compareTo(right);
            }
        });
        return list;
    }
}