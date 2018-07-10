package ru.job4j.jdbc;

import javax.xml.bind.annotation.*;
import java.util.List;


/**
 * Entries
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.07.10
 */
@XmlRootElement
public class Entries {

    private List<Entry> entries;

    /**
     * Присваиваем значение для entries
     *
     * @param entries список entry
     */
    @XmlElement(name = "entry")
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }


    /**
     * Возвращаем список с Entry
     *
     * @return результирующий список
     */
    public List<Entry> getEntries() {
        return this.entries;
    }
}
