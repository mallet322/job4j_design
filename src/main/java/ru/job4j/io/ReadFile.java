package ru.job4j.io;

import java.io.FileInputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile {

    public static List<Integer> getEvenNumbers(String fileName) {
        var numbers = readFile(fileName).split(System.lineSeparator());
        return Stream.of(numbers)
                     .map(Integer::valueOf)
                     .filter(num -> num % 2 == 0)
                     .collect(Collectors.toList());
    }

    private static String readFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return null;
        }
        var text = new StringBuilder();
        try (FileInputStream in = new FileInputStream(fileName)) {
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text.toString();
    }

}
