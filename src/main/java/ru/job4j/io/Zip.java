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

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getName()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        if (args.length == 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        ArgsName jvm = ArgsName.of(args);
        var root = Path.of(jvm.get("d"));
        if (!root.toFile().exists()) {
            throw new IllegalArgumentException("Root folder is not exist");
        }
        var exclude = jvm.get("e");
        var output = new File(jvm.get("o"));
        var list = Search.search(root, p -> !p.toFile().getName().endsWith(exclude))
                         .stream()
                         .map(Path::toFile)
                         .collect(Collectors.toList());
        packFiles(list, output);
    }

}
