package ru.job4j.generic;

/**
 * Interface Store.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.09
 */
public interface Store<T extends Base> {

    /**
     * Добавляем элемент в список
     *
     * @param model элемент
     */
    void add(T model);


    /**
     * Меняем элемент в списке по совпадению id
     *
     * @param id    ID
     * @param model новый элемент
     * @return true/false успешности
     */
    boolean replace(String id, T model);


    /**
     * Удаляем элемент в списке по ID
     *
     * @param id ID элемента
     * @return true/false
     */
    boolean delete(String id);


    /**
     * Ищем элемент в списке по ID
     *
     * @param id ID
     * @return элемент
     */
    T findById(String id);

}
