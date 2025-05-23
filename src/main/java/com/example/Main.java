package com.example;

import com.example.MessageModels.MessageTypes;

public class Main {
    public static void main(String[] args) {
        String type = "AUTH_REQUEST";

        MessageTypes msgType = MessageTypes.valueOf(type);
        System.out.println(msgType.toString());
    }
}