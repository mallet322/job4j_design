package ru.job4j.io.search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        String path = args[0];
        String ext = args[1];
        if (!"java".equals(ext)
                && !"txt".equals(ext)
                && !"yml".equals(ext)
                && !"xml".equals(ext)
                && !"json".equals(ext)
                && !"properties".equals(ext)) {
            throw new IllegalArgumentException(
                    "The file extension is incorrect. Usage java, txt, yml, json, xml extensions");
        }
        Path start = Paths.get(path);
        search(start, p -> p.toFile().getName().endsWith(ext)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

}