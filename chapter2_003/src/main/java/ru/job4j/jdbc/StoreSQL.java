package ru.job4j.jdbc;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StoreSQL
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.07.10
 */
public class StoreSQL implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(StoreSQL.class);
    private Connection connection;
    private Files files = new Files();


    /**
     * Конструктор
     *
     * @param config класс с кофигурациями
     */
    public StoreSQL(Config config) {
        BasicConfigurator.configure();
        try {
            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", config.getProperty("database")),
                    config.getProperty("user"), config.getProperty("password"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(files.getDataFromFile("src//main//resources//testdb.sql"))) {
            statement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    /**
     * Генерируем N записе
     *
     * @param n кол-во записей
     */
    public void generate(int n) {
        try (PreparedStatement delete = connection.prepareStatement("delete from entry;");
             PreparedStatement insert = connection.prepareStatement("insert into entry(field) values(?);")) {
            connection.setAutoCommit(false);
            delete.execute();
            for (int index = 1; index <= n; index++) {
                insert.setInt(1, index);
                insert.addBatch();
            }
            insert.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage(), ex);
            }
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Возвращаем список с данными
     *
     * @return список сущностей
     */
    public List<Entry> getEntry() {
        List<Entry> result = new ArrayList<>();
        try (PreparedStatement select = connection.prepareStatement("select field from entry;");
             ResultSet rs = select.executeQuery()) {
            while (rs.next()) {
                Entry entry = new Entry();
                entry.setField(rs.getInt(1));
                result.add(entry);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Закрываем коннект
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
