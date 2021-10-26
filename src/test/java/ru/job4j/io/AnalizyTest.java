package ru.job4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenGetServerDowntimeInTemporaryFile() throws IOException {
        String source = "src/main/resources/data/server.log";
        File target = folder.newFile("target.txt");
        Analizy.unavailable(source, target.getAbsolutePath());
        String expected = "10:57:01;11:02:02;";
        StringBuilder actual = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(actual::append);
        }
        Assert.assertThat(expected, Matchers.is(actual.toString()));
    }

    @Test
    public void whenGetServerDowntimeInTemporaryFileOnOneDiapason() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("400 10:58:01");
            out.println("200 11:02:02");
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String expected = "10:58:01;11:02:02;";
        StringBuilder actual = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(actual::append);
        }
        Assert.assertThat(expected, Matchers.is(actual.toString()));
    }

    @Test
    public void whenGetServerDowntimeInTemporaryFileOnTwoDiapason() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("400 10:58:01");
            out.println("200 11:02:02");
            out.println("200 11:03:02");
            out.println("500 12:02:02");
            out.println("400 12:05:02");
            out.println("300 13:02:02");
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String expected = "10:58:01;11:02:02;12:02:02;13:02:02;";
        StringBuilder actual = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(actual::append);
        }
        Assert.assertThat(expected, Matchers.is(actual.toString()));
    }

}