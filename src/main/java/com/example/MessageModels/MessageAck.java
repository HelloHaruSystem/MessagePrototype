package com.example.MessageModels;

public class MessageAck implements msg {
    private String messageId;
    private String status; // "received", "read", "error"
    private String errorMessage;
        
    public MessageAck(String messageId, String status) {
        this.messageId = messageId;
        this.status = status;
    }
        
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
