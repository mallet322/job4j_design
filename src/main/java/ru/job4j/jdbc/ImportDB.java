package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportDB {

    private static final Logger LOG = LoggerFactory.getLogger(ImportDB.class.getName());
    private final Properties cfg;
    private final String dump;
    private Connection connection;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
        initConnection();
    }

    private void initConnection() {
        try {
            connection = getConnection();
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }

    private Connection getConnection() throws Exception {
        Class.forName(cfg.getProperty("jdbc.driver"));
        return DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password"));
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines()
              .forEach(s -> {
                  var strings = s.split(";");
                  users.add(new User(strings[0], strings[1]));
              });
        }
        return users;
    }

    public void initDB() {
        try (Statement statement = connection.createStatement()) {
            String ddl = "create table users(name varchar(255), email varchar(255));";
            statement.execute(ddl);
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        for (User user : users) {
            try (PreparedStatement ps = connection.prepareStatement("insert into users(name, email) values(?, ?)")) {
                ps.setString(1, user.name);
                ps.setString(2, user.email);
                ps.execute();
            }
        }
    }

    private static class User {

        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("src/main/resources/spammer.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "src/main/resources/dump.txt");
        db.initDB();
        db.save(db.load());
    }

}
