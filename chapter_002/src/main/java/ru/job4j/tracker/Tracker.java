package ru.job4j.tracker;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Tracker
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.21
 */

public class Tracker implements AutoCloseable {

    private Connection connection;

    private static final Logger LOG = LogManager.getLogger(Tracker.class);

    private String path = "src//main//resources//";

    public Tracker(Config config) {
        try {
            connection = DriverManager.getConnection(String.format("jdbc:postgresql://%s/%s", config.getProperty("url"), config.getProperty("database")),
                    config.getProperty("user"), config.getProperty("password"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(getQueryFromFile(path, "item.sql"))) {
            statement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Добавляем элемент в трекер
     *
     * @param item таска
     * @return добавленное значение.
     */
    public Item add(Item item) {
        try (PreparedStatement statement = connection.prepareStatement("insert into public.item(name, description) values (?, ?) returning id, creationdate;")) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDesc());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                item.setID(rs.getString(1));
            }
            item.setTime(LocalDateTime.parse(rs.getString(2).replace(' ', 'T')));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }


    /**
     * Меняем элемент в трекере по ID
     *
     * @param id   id таски
     * @param item таска
     */
    public void replace(String id, Item item) {
        try (PreparedStatement statement = connection.prepareStatement("update public.item set name = ?, description = ? where id = ?;")) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDesc());
            statement.setInt(3, Integer.parseInt(id));
            statement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    /**
     * Удаляем элемент по уникальной ID
     *
     * @param id уникальное ID
     */
    public void delete(String id) {
        try (PreparedStatement statement = connection.prepareStatement("delete from public.item where id = ?;")) {
            statement.setInt(1, Integer.parseInt(id));
            statement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    /**
     * Возвращаем список тасок
     *
     * @return список тасок.
     */
    public Item[] findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select id, name, description, creationdate from public.item;");
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                items.add(new Item(rs.getString(1), rs.getString(2), rs.getString(3), LocalDateTime.parse(rs.getString(4).replace(' ', 'T'))));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return items.toArray(new Item[items.size()]);
    }

    /**
     * Ищем все таски по указанному имени
     *
     * @param key название таски
     * @return список тасок с одинаковым названием.
     */
    public Item[] findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select id, name, description, creationdate from public.item where name = ?;")) {
            statement.setString(1, key);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                items.add(new Item(rs.getString(1), rs.getString(2), rs.getString(3), LocalDateTime.parse(rs.getString(4).replace(' ', 'T'))));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return items.toArray(new Item[items.size()]);
    }

    /**
     * Ищем таску по уникальному ID
     *
     * @param id уникальное id
     * @return возвращаем элемент списка или Null, если не нашли
     */
    public Item findByID(String id) {
        Item result = null;
        try (PreparedStatement statement = connection.prepareStatement("select id, name, description, creationdate from public.item where id = ?;")) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = new Item(rs.getString(1), rs.getString(2), rs.getString(3),
                        LocalDateTime.parse(rs.getString(4).replace(' ', 'T')));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }

    /**
     * Возвращаем запрос из файла
     *
     * @param path путь к файлу
     * @param file название файла
     * @return запрос
     */
    private String getQueryFromFile(final String path, final String file) {
        StringBuilder result = new StringBuilder();
        try (FileInputStream input = new FileInputStream(String.valueOf(path + file))) {
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
