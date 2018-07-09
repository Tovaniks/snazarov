package ru.job4j.tracker;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private Properties configFile = new Properties();
    public Config() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
            configFile.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return this.configFile.getProperty(key);
    }
}
