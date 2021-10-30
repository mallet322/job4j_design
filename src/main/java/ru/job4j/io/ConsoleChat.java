package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        String answer;
        String phrase = null;
        boolean isStop = true;
        while (!OUT.equals(phrase)) {
            phrase = sc.nextLine();
            writeLog(log, phrase, 1);
            if (STOP.equals(phrase)) {
                answer = "BOT STOPPING";
                System.out.println(answer);
                writeLog(log, answer, 2);
                isStop = false;
            } else if (CONTINUE.equals(phrase)) {
                answer = "BOT STARTING";
                System.out.println(answer);
                writeLog(log, answer, 2);
                isStop = true;
            }
            if (isStop) {
                if (OUT.equals(phrase)) {
                    answer = "EXIT";
                } else {
                    answer = getRandomPhrase(answers);
                }
                System.out.println(answer);
                writeLog(log, answer, 2);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            phrases = br.lines()
                        .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void writeLog(List<String> log, String str, int check) {
        if (check == 1) {
            str = "User: ".concat(str);
            log.add(str);
        } else if (check == 2) {
            str = "Bot: ".concat(str);
            log.add(str);
        }
    }

    private String getRandomPhrase(List<String> answers) {
        Random rnd = new Random();
        return answers.get(rnd.nextInt(answers.size()));
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc =
                new ConsoleChat("src/main/resources/bot/bot_log.txt", "src/main/resources/bot/bot_answers.txt");
        cc.run();
    }

}
