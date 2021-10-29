package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import ru.job4j.io.search.Search;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateArguments(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        for (String s : args) {
            var strings = s.split("=");
            if (!strings[0].startsWith("-")) {
                throw new IllegalArgumentException("Wrong argument type! Arg key should be start with '-'");
            }
            switch (strings[0]) {
                case "-d":
                    var file = new File(strings[1]);
                    if (!file.exists() || !file.isDirectory()) {
                        throw new IllegalArgumentException("Root folder is not directory or not exist!");
                    }
                    break;
                case "-e":
                    if (strings[1] == null || strings[1].isEmpty()) {
                        throw new IllegalArgumentException("Wrong argument type! Arg value is null!");
                    }
                    break;
                case "-o":
                    if (strings[1] == null || strings[1].isEmpty()) {
                        throw new IllegalArgumentException("Wrong argument type! Arg value is null!");
                    } else if (!strings[1].endsWith("zip")) {
                        throw new IllegalArgumentException("Wrong argument type! Package file ext is not zip!");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Wrong argument type!");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        validateArguments(args);
        ArgsName jvm = ArgsName.of(args);
        var root = Path.of(jvm.get("d"));
        var exclude = jvm.get("e");
        var output = new File(jvm.get("o"));
        var list = Search.search(root, p -> !p.toFile().getName().endsWith(exclude))
                         .stream()
                         .map(Path::toFile)
                         .collect(Collectors.toList());
        packFiles(list, output);
    }

}
