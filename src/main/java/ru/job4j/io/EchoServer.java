package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String line = in.readLine();
                    System.out.println(line);
                    if (in.ready()) {
                        String str = extractRequestParam(line);
                        serverResponse(str, out, server);
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void serverResponse(String msg, OutputStream out, ServerSocket server) throws IOException {
        if ("Hello".equals(msg)) {
            out.write("Hello, dear friend.\r\n\r\n".getBytes());
        } else if ("Exit".equals(msg)) {
            out.write("Exit...\r\n\r\n".getBytes());
            server.close();
        } else {
            out.write("Request parameter: ".concat(msg).concat("\r\n\r\n").getBytes());
        }
    }

    private static String extractRequestParam(String request) {
        return request == null || request.isEmpty() ? " " : extractParam(request);
    }

    private static String extractParam(String request) {
        return request.contains("/?msg=")
               ? request.substring(request.indexOf("/?msg="), request.lastIndexOf(" HTTP/1.1")).split("=")[1]
               : " ";
    }

}
