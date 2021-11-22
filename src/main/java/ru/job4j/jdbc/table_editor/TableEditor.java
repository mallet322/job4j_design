package ru.job4j.jdbc.table_editor;

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
            LOG.error("InitConnectionError", e);
        }
    }

    public Connection getConnection() throws Exception {
        Class.forName(properties.getProperty(TableEditorConstants.DRIVER));
        return DriverManager.getConnection(
                properties.getProperty(TableEditorConstants.URL),
                properties.getProperty(TableEditorConstants.LOGIN),
                properties.getProperty(TableEditorConstants.PASSWORD));
    }

    public void createQuery(String ddl, String... params) {
        try (Statement statement = connection.createStatement()) {
            String query = checkQuery(ddl, params);
            statement.execute(query);
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }

    private String checkQuery(String ddl, String... params) {
        switch (ddl) {
            case TableEditorConstants.CREATE_TABLE -> {
                return createTable(params[0]);
            }
            case TableEditorConstants.DROP_TABLE -> {
                return dropTable(params[0]);
            }
            case TableEditorConstants.ADD_COLUMN -> {
                return addColumn(params[0], params[1], params[2]);
            }
            case TableEditorConstants.DROP_COLUMN -> {
                return dropColumn(params[0], params[1]);
            }
            case TableEditorConstants.RENAME_COLUMN -> {
                return renameColumn(params[0], params[1], params[2]);
            }
            default -> {
                return " ";
            }
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

    private String createTable(String tableName) {
        return String.format("create table %s();", tableName);
    }

    private String dropTable(String tableName) {
        return String.format("drop table %s;", tableName);
    }

    private String addColumn(String tableName, String columnName, String type) {
        return String.format("alter table %s add column %s %s;", tableName, columnName, type);
    }

    private String dropColumn(String tableName, String columnName) {
        return String.format("alter table %s drop column %s;", tableName, columnName);
    }

    private String renameColumn(String tableName, String columnName, String newColumnName) {
        return String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName);
    }

    public static void main(String[] args) throws Exception {
        var settings = Settings.getInstance();
        settings.setPropertiesFile(TableEditorConstants.PATH);
        var properties = settings.getProperties();
        TableEditor editor = new TableEditor(properties);
        editor.createQuery(TableEditorConstants.CREATE_TABLE, "test");
        editor.createQuery(TableEditorConstants.ADD_COLUMN, "test", "id", "serial primary key");
        editor.createQuery(TableEditorConstants.ADD_COLUMN, "test", "id1", "int");
        editor.createQuery(TableEditorConstants.DROP_COLUMN, "test", "id1");
        editor.createQuery(TableEditorConstants.ADD_COLUMN, "test", "id1", "int");
        editor.createQuery(TableEditorConstants.RENAME_COLUMN, "test", "id1", "price");
        System.out.println(getTableScheme(editor.getConnection(), "test"));
    }

}