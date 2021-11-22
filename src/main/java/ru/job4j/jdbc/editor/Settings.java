package ru.job4j.jdbc.editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Settings {

    private static final Logger LOG = LoggerFactory.getLogger(Settings.class.getName());

    private final Properties properties = new Properties();

    private static Settings instance;

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public void setPropertiesFile(String filePath) {
        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            this.properties.load(fis);
        } catch (IOException e) {
            LOG.error("Settings error", e);
        }
    }

    public String getValue(String key) {
        return this.properties.getProperty(key);
    }

    public Properties getProperties() {
        return this.properties;
    }

}

