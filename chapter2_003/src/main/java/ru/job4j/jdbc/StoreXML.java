package ru.job4j.jdbc;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;


/**
 * StoreXML
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.07.10
 */
public class StoreXML {

    private static final Logger LOG = LogManager.getLogger(StoreSQL.class);
    private File target;

    /**
     * Конструктор
     *
     * @param target файл назначения
     */
    public StoreXML(File target) {
        BasicConfigurator.configure();
        this.target = target;
    }

    /**
     * Сохраяем полученный список с сущностями в виде XML
     *
     * @param list XML
     */
    public void save(List<Entry> list) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Entries entries = new Entries();
            entries.setEntries(list);
            jaxbMarshaller.marshal(entries, target);

        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
