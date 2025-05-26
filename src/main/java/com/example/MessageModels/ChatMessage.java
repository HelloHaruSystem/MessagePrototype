package com.example.MessageModels;

public class ChatMessage implements msg {
    private String sender;
    private String channel;
    private String content;
    private boolean encrypted;
    private MessageMetadata metadata;
        
    public ChatMessage(String sender, String channel, String content, boolean encrypted) {
        this.sender = sender;
        this.channel = channel;
        this.content = content;
        this.encrypted = encrypted;
        this.metadata = new MessageMetadata();
    }
        
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }
    
    public MessageMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(MessageMetadata metadata) {
        this.metadata = metadata; 
    }
    
}
