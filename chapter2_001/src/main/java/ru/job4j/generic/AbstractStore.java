package ru.job4j.generic;


/**
 * Class AbstractStore.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.09
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    SimpleArray<T> elements = new SimpleArray<>();

    /**
     * Возвращаем порядковый номер элемента в списке. Если не найден, возращаем -1.
     *
     * @param id ID элемента
     * @return index элемента.
     */
    private int getIndex(String id) {
        int result = -1;
        int index = 0;
        for (T element : elements) {
            if (element.getId().equals(id)) {
                result = index;
                break;
            }
            index++;
        }
        return result;
    }

    /**
     * Добавляем элемент в список
     *
     * @param model элемент
     */
    @Override
    public void add(T model) {
        elements.add(model);
    }


    /**
     * Меняем элемент в списке по совпадению id
     *
     * @param id    ID
     * @param model новый элемент
     * @return true/false успешности
     */
    @Override
    public boolean replace(String id, T model) {
        boolean success = false;
        int index = getIndex(id);
        if (index != -1) {
            elements.set(index, model);
            success = true;
        }
        return success;
    }


    /**
     * Удаляем элемент в списке по ID
     *
     * @param id ID элемента
     * @return true/false
     */
    @Override
    public boolean delete(String id) {
        boolean success = false;
        int index = getIndex(id);
        if (index != -1) {
            elements.delete(index);
            success = true;
        }
        return success;
    }


    /**
     * Ищем элемент в списке по ID
     *
     * @param id ID
     * @return элемент
     */
    @Override
    public T findById(String id) {
        T result = null;
        int index = getIndex(id);
        if (index != -1) {
            result = elements.get(index);
        }
        return result;
    }

}



