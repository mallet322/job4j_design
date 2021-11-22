package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import ru.job4j.io.Config;

public class ConnectionDemo {

    private static final String PATH = "src/main/resources/app.properties";
    private static final String DRIVER = "driver";
    private static final String URL = "url";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        var properties = getProperties();
        Class.forName(properties.value(DRIVER));
        String url = properties.value(URL);
        String login = properties.value(LOGIN);
        String password = properties.value(PASSWORD);
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }

    private static Config getProperties() {
        Config config = new Config(PATH);
        config.load();
        return config;
    }

}