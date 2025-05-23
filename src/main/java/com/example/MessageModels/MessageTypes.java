package com.example.MessageModels;

public enum MessageTypes {
    AUTH_REQUEST,
    AUTH_RESPONSE,
    CHAT_MESSAGE,
    CHANNEL_JOIN,
    CHANNEL_LEAVE,
    MESSAGE_ACK,
    ERROR,
    USER_LIST,
    CHANNEL_LIST;

    @Override
    public String toString() {
        return name();
    }
}
