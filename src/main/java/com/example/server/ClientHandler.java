package com.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.MessageModels.ErrorMessage;
import com.example.MessageModels.Message;
import com.example.MessageModels.MessageTypes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class ClientHandler implements Runnable {
    private static final AtomicInteger messageCounter = new AtomicInteger(0);
    private static final Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    private static final Map<String, Set<String>> channels = new ConcurrentHashMap<>();

    private final Socket socket;
    private final Gson gson;
    private BufferedReader reader;
    private PrintWriter writer;
    private String username;
    private String userId;
    private boolean authenticated = false;
    private Set<String> joinedChannels = new HashSet<>();

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
        
        try {
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.writer = new PrintWriter(this.socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error setting up client handler: " + e.getMessage());
        }
    }

    public void run() {
        try {
            String inputLine;
            while((inputLine = reader.readLine()) != null) {
                handleMessage(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected: " + this.username);
        } finally {
            cleanup();
        }
    }

    
    private void handleMessage(String jsonMessage) {
        try {
            JsonObject messageObject = this.gson.fromJson(jsonMessage, JsonObject.class);
            String type = messageObject.get("type").getAsString();

            System.out.println("Received message type: " + type + " from " + (this.username != null ? this.username : "unauthenticated client"));
            MessageTypes msgType = MessageTypes.valueOf(type);

            switch (msgType) {
                case AUTH_REQUEST:
                    
                    break;
                 
                case CHAT_MESSAGE:

                    break;

                case CHANNEL_JOIN:

                    break;

                case CHANNEL_LEAVE:

                    break;
                
                default:
                    sendError("UNKNOWN_TYPE", "Unknown message type: " + type);
                    break;
            }

        } catch (JsonSyntaxException e) {
            sendError("INVALID_JSON", "Unknown message tye: " + e.getMessage());
        } catch (Exception e) {
            sendError("PROCESSING_ERROR", "Unknown message type: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void leaveChannel(String channel) {
        if (!this.joinedChannels.contains(channel)) {
            sendError("NOT_IN_CHANNEL", "Not in channel" +  channel);
            return;
        }

        // remove user from channel
        Set<String> channelUsers = channels.get(channel);
        if (channelUsers != null) {
            channelUsers.remove(this.username);
            if(channelUsers.isEmpty()) {
                channels.remove(channel);
            }
        }
    }

    private void sendMessage(String type, Object payload) {
        Message message = new Message(type, generateMessageId(), payload);
        this.writer.println(gson.toJson(message));
    }

    private void sendError(String code, String errorMessage) {
        ErrorMessage error = new ErrorMessage(code, errorMessage);
        sendMessage(MessageTypes.ERROR.toString(), error);
    }

    // TODO: let database handle?
    private String generateMessageId() {
        return "msg-" + System.currentTimeMillis() + "-" + messageCounter.incrementAndGet();
    }

    private void cleanup() {
        // remove from channels
        for (String channel : new HashSet<>(this.joinedChannels)) {
            leaveChannel(channel);
        }

        // remove from connected clients
        if (username != null) {
            clients.remove(username);
        }

        // close connections
        try {
            if (this.reader != null) {
                this.reader.close();
            }
            if (this.writer != null) {
                this.writer.close();
            }
            if (this.socket != null) {
                this.socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error during cleanup: " + e.getMessage());
        }
    }

    // getters
    public String getUsername() {
        return this.username;
    }

    public boolean isAuthenticated() {
        return this.authenticated;
    }

    public Set<String> getJoinedChannels() {
        return new HashSet<>(this.joinedChannels);
    }
}
