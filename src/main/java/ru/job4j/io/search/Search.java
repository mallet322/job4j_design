package ru.job4j.io.search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        validateArguments(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static void validateArguments(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid arguments. Usage java -jar dir.jar ROOT_FOLDER EXT");
        }
        var file = Path.of(args[0]).toFile();
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException(
                    "Root folder is null or not exist. Usage java -jar dir.jar ROOT_FOLDER.");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

}