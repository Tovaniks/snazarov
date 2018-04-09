package ru.job4j.generic;

/**
 * Class RoleStore.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.09
 */
public class RoleStore extends AbstractStore<Role> implements Store<Role> {

    /**
     * Добавляем элемент в список
     *
     * @param model элемент
     */
    @Override
    public void add(Role model) {
        super.add(model);
    }


    /**
     * Меняем элемент в списке по совпадению id
     *
     * @param id    ID
     * @param model новый элемент
     * @return true/false успешности
     */
    @Override
    public boolean replace(String id, Role model) {
        return super.replace(id, model);
    }

    /**
     * Удаляем элемент в списке по ID
     *
     * @param id ID элемента
     * @return true/false
     */
    @Override
    public boolean delete(String id) {
        return super.delete(id);
    }


    /**
     * Ищем элемент в списке по ID
     *
     * @param id ID
     * @return элемент
     */
    @Override
    public Role findById(String id) {
        return super.findById(id);
    }
}
