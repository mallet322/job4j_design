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
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        serverResponse(str, out, server);
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void serverResponse(String msg, OutputStream out, ServerSocket server) throws IOException {
        if (msg.contains("/?msg=Hello ")) {
            out.write("Hello, dear friend.\r\n\r\n".getBytes());
        } else if (msg.contains("/?msg=Any ")) {
            out.write("Whats going on, dear friend?\r\n\r\n".getBytes());
        } else if (msg.contains("/?msg=Exit ")) {
            out.write("Exit.\r\n\r\n".getBytes());
            server.close();
        }
    }

}

