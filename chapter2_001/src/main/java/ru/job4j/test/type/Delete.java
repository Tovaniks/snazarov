package ru.job4j.test.type;


/**
 * Class Delete
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.23
 */
public class Delete implements Type {

    /**
     * Удаление заявки
     *
     * @return Delete.
     */
    @Override
    public String type() {
        return "Delete";
    }

}
