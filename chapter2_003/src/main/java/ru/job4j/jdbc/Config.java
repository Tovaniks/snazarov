package ru.job4j.jdbc;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Config
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.07.10
 */
public class Config {
    private static final Logger LOG = LogManager.getLogger(StoreSQL.class);
    private Properties configFile = new Properties();

    /**
     * Конструктор. Подключаемся к файлу с настройками БД.
     */
    public Config() {
        BasicConfigurator.configure();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("connection.properties")) {
            configFile.load(is);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    /**
     * Возвращаем настройки БД по ключу
     *
     * @param key ключ
     * @return настройка
     */
    public String getProperty(String key) {
        return this.configFile.getProperty(key);
    }
}
