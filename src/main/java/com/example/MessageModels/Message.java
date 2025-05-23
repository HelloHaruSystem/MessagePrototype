package com.example.MessageModels;

public class Message {
    private String type;
    private String id;
    private String timestamp;
    private Object payload;

    public Message(String type, String id, Object payload) {
        this.type = type;
        this.id = id;
        this.payload = payload;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return this.timestamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timestamp = timeStamp;
    }

    public Object getPayload() {
        return this.payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
