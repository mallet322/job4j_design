package ru.job4j.jdbc.editor;

import org.junit.Assert;
import org.junit.Test;

public class SettingsTest {

    @Test
    public void whenCreatePropertiesAndGetValue() {
        var settings = Settings.getInstance();
        settings.setPropertiesFile(TableEditorConstants.PATH);
        Assert.assertEquals("org.postgresql.Driver", settings.getValue(TableEditorConstants.DRIVER));
        Assert.assertEquals("jdbc:postgresql://localhost:5432/postgres", settings.getValue(TableEditorConstants.URL));
        Assert.assertEquals("postgres", settings.getValue(TableEditorConstants.LOGIN));
        Assert.assertEquals("postgres", settings.getValue(TableEditorConstants.PASSWORD));
    }

}