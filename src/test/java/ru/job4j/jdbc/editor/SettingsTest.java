package ru.job4j.jdbc.editor;

import org.junit.Assert;
import org.junit.Test;

public class SettingsTest {

    @Test
    public void whenCreatePropertiesAndGetValue() {
        var settings = Settings.getInstance();
        settings.setPropertiesFile("src/main/resources/app.properties");
        Assert.assertEquals("org.postgresql.Driver", settings.getValue("driver"));
        Assert.assertEquals("jdbc:postgresql://localhost:5432/postgres", settings.getValue("url"));
        Assert.assertEquals("postgres", settings.getValue("login"));
        Assert.assertEquals("postgres", settings.getValue("password"));
    }

}