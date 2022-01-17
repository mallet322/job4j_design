package ru.job4j.ood.ocp;

/**
 * 1 пример.
 *
 * Клиент и сервер.
 *
 * В данном случае клиент напрямую обращается к серверу, что нарушет принцип OCP.
 * Если клиенту придется обращаться к различным серверам, то приедсят рефакторить два класса.
 * Решением будет реализовать абстракцию от сервера, на которую будет ссылаться клиент,
 * тем самым мы сможем гибко реализовать общение клиента с различными серверами.
 */
public class Server {

    private final Client client;

    public Server(Client client) {
        this.client = client;
    }

    public void readRequestForClient() {
        client.sendRequest();
    }

    public static class Client {

        public void sendRequest() {
            System.out.println("Hello");
        }

    }

}
