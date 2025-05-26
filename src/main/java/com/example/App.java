package com.example;

import com.example.server.ChatServer;

public class App {
    public static void main(String[] args) {
        ChatServer server = new ChatServer(8080);
        server.start();
    }
}
