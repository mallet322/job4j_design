package ru.job4j.gc.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder result = new StringBuilder();
        String filePath = cachingDir + key;
        try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
            while (in.ready()) {
                result.append(in.readLine())
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}