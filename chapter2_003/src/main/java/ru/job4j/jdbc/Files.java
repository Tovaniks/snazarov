package ru.job4j.jdbc;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Files
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.07.10
 */
public class Files {

    private static final Logger LOG = LogManager.getLogger(StoreSQL.class);

    /**
     * Возвращаем текст запросов по указанному пути
     *
     * @param path путь
     * @return запрос
     */
    public String getDataFromFile(final String path) {
        BasicConfigurator.configure();
        StringBuilder result = new StringBuilder();
        try (FileInputStream input = new FileInputStream(String.valueOf(path))) {
            int data = input.read();
            while (data != -1) {
                result.append((char) data);
                data = input.read();
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return result.toString();
    }
}
