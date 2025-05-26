package com.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    private static final int POOL_SIZE = 10;
    
    private int port;
    private final ExecutorService threadPool;
    private ServerSocket serverSocket;
    private boolean running;

    public ChatServer(int port) {
        this.port = port;
        this.threadPool = Executors.newFixedThreadPool(POOL_SIZE);
        this.running = false;
    }

    public void start() {
        try {
            this.serverSocket = new ServerSocket(this.port);
            this.running = true;

            System.out.println("Chat Server starting on port " + this.port + "...");
            System.out.println("Waiting for clients...");

            while (running) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("new Client connected from " + clientSocket.getInetAddress());

                // run client handlers in their own thread
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                threadPool.execute(clientHandler);
            } 
        }catch (IOException e) {
            if (this.running) {
                System.out.println("Error accepting clients connection " + e.getMessage());
            }
        } finally {
            stop();
        }
    }

    public void stop() {
        this.running = false;

        try {
            if (this.serverSocket != null && !this.serverSocket.isClosed()) {
                this.serverSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing server gracefully " + e.getMessage());
            e.printStackTrace();
        }

        this.threadPool.shutdown();
        System.out.println("Server stopped");
    }
    
}