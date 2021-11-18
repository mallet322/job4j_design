package ru.job4j.io.finder;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import ru.job4j.io.ArgsName;

public class Application {

    public static void main(String[] args) {
        Application app = new Application();
        var argsName = app.validateArguments(args);
        app.start(argsName);
    }

    public void start(ArgsName argsName) {
        var path = argsName.get("d");
        var name = argsName.get("n");
        var namePattern = argsName.get("t");
        var out = argsName.get("o");
        var pathList = search(path, name, namePattern);
        writeToFile(pathList, out);
    }

    public ArgsName validateArguments(String[] args) {
        var argsName = ArgsName.of(args);
        var keySet = argsName.getValues().keySet();
        if (args.length != 4
                && !keySet.contains("d")
                && !keySet.contains("n")
                && !keySet.contains("t")
                && !keySet.contains("o")) {
            throw new IllegalArgumentException(
                    "Invalid arguments!"
                            + "Use java -jar finder.jar "
                            + "-d=<path> "
                            + "-n=<file_name> "
                            + "-t=<filter_type>: name - fileName, mask, regex."
                            + "-o=<output_path>");
        }
        var path = Path.of(argsName.get("d"));
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory is not exist!");
        }
        return argsName;
    }

    private List<Path> search(String path, String name, String filterType) {
        var root = Path.of(path);
        var predicate = FileFilter.getFilter().getPredicate(filterType, name);
        FileVisitor searcher = new FileVisitor(predicate);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

    private void writeToFile(List<Path> list, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            list.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
