package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Set<FileProperty> files = new HashSet<>();
    private final Set<FileProperty> duplicates = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        FileProperty currentFile = new FileProperty(attrs.size(), file.getFileName().toString());
        if (!files.add(currentFile)) {
           duplicates.add(currentFile);
        }
        return FileVisitResult.CONTINUE;
    }

    public Set<FileProperty> getFiles() {
        return duplicates;
    }

}
