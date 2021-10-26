package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {

    public static void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String temp = null;
            StringBuilder downtime = new StringBuilder();
            while (in.ready()) {
                String s = in.readLine();
                if (temp == null && (s.startsWith("400") || s.startsWith("500"))) {
                    temp = s.split(" ")[1].concat(";");
                    downtime.append(temp);
                } else if (temp != null && (s.startsWith("200") || s.startsWith("300"))) {
                    temp = s.split(" ")[1].concat(";");
                    downtime.append(temp);
                    temp = null;
                }
            }
            out.println(downtime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("src/main/resources/data/server.log", "src/main/resources/data/server-downtime.txt");
    }

}
