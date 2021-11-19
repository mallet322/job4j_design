package ru.job4j.io.finder;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FileFilter {

    private static FileFilter instance;

    public static FileFilter getFilter() {
        if (instance == null) {
            instance = new FileFilter();
        }
        return instance;
    }

    public Predicate<Path> getPredicate(String searchType, String pattern) {
        Predicate<Path> predicate;
        switch (searchType) {
            case "name":
                predicate = getFileByName(pattern);
                break;
            case "mask":
                predicate = getFileByMask(pattern);
                break;
            case "regex":
                predicate = getFileByRegexPattern(pattern);
                break;
            default:
                throw new IllegalArgumentException("Wrong argument!");
        }
        return predicate;
    }

    private Predicate<Path> getFileByName(String name) {
        return path -> name.equals(path.toFile().getName());
    }

    private Predicate<Path> getFileByMask(String mask) {
        var p =
                mask.replace(".", "\\.")
                    .replace("*", ".*")
                    .replace("?", ".?")
                    .replace("*", ".*?");
        Pattern pattern = Pattern.compile(p);
        return path -> pattern.matcher(path.toFile().getName()).matches();
    }

    private Predicate<Path> getFileByRegexPattern(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return path -> pattern.matcher(path.toFile().getName()).matches();
    }

}
