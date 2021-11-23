package ru.job4j.jdbc.editor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableEditor implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(TableEditor.class.getName());

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            connection = getConnection();
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }

    public Connection getConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        return DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"));
    }

    private void createQuery(String ddl) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(ddl);
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                                         metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public void createTable(String tableName) {
        createQuery(String.format("create table %s();", tableName));
    }

    public void dropTable(String tableName) {
        createQuery(String.format("drop table %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) {
        createQuery(String.format("alter table %s add column %s %s;", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) {
        createQuery(String.format("alter table %s drop column %s;", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        createQuery(String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName));
    }

    public static void main(String[] args) throws Exception {
        var settings = Settings.getInstance();
        settings.setPropertiesFile("src/main/resources/app.properties");
        var properties = settings.getProperties();
        TableEditor editor = new TableEditor(properties);
        editor.createTable("test");
        editor.addColumn( "test", "id", "serial primary key");
        editor.addColumn( "test", "id1", "int");
        editor.dropColumn( "test", "id1");
        editor.addColumn("test", "id1", "int");
        editor.renameColumn( "test", "id1", "price");
        System.out.println(getTableScheme(editor.getConnection(), "test"));
        editor.dropTable( "test");
    }

}