package com.example.MessageModels;

public class ErrorMessage {
    private String code;
    private String message;
    private String details;
        
    public ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
        
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}