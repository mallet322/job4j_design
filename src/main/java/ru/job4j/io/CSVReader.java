package ru.job4j.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {

    public void handle(ArgsName argsName) {
        var path = argsName.get("path");
        var delimiter = argsName.get("delimiter");
        var out = argsName.get("out");
        var filter = argsName.get("filter");
        var data = readData(path, delimiter);
        var filteredData = dataFilter(data, filter, delimiter);
        printOrSaveTargetFile(out, filteredData);
    }

    private List<List<String>> readData(String path, String delimiter) {
        List<List<String>> data = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(path))) {
            while (sc.hasNextLine()) {
                var lines = readLines(sc, delimiter);
                data.add(lines);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    private List<String> readLines(Scanner sc, String delimiter) {
        Scanner lineScanner = new Scanner(sc.nextLine()).useDelimiter(delimiter);
        List<String> lines = new ArrayList<>();
        while (lineScanner.hasNext()) {
            lines.add(lineScanner.next());
        }
        return lines;
    }

    private List<String> dataFilter(List<List<String>> data, String filter, String delimiter) {
        var header = data.get(0);
        var headerIndexes = extractHeaderIndexes(header, filter);
        var result = new ArrayList<String>();
        for (var list : data) {
            var element = getElement(headerIndexes, list, delimiter);
            result.add(element.toString());
        }
        return result;
    }

    private static List<Integer> extractHeaderIndexes(List<String> header, String filter) {
        List<Integer> columns = new ArrayList<>();
        for (var s : header) {
            if (filter.contains(s)) {
                columns.add(header.indexOf(s));
            }
        }
        return columns;
    }

    private StringJoiner getElement(List<Integer> indexes, List<String> list, String delimiter) {
        StringJoiner sj = new StringJoiner(delimiter);
        for (var index : indexes) {
            var el = list.get(index);
            sj.add(el);
        }
        return sj;
    }

    private void printOrSaveTargetFile(String out, List<String> data) {
        if ("stdout".equals(out)) {
            printTarget(data);
        } else {
            saveTarget(data, out);
        }
    }

    private void printTarget(List<String> list) {
        list.forEach(System.out::println);
    }

    private void saveTarget(List<String> log, String out) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(out, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArgsName validateArguments(String[] args) {
        var argsName = ArgsName.of(args);
        var keySet = argsName.getValues().keySet();
        if (args.length != 4
                && !keySet.contains("path")
                && !keySet.contains("delimiter")
                && !keySet.contains("out")
                && !keySet.contains("filter")) {
            throw new IllegalArgumentException("Invalid arguments!");
        }
        var file = new File(argsName.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException("File is not exist!");
        }
        return argsName;
    }

    public static void main(String[] args) throws Exception {
        CSVReader reader = new CSVReader();
        var argsName = reader.validateArguments(args);
        reader.handle(argsName);
    }

}
