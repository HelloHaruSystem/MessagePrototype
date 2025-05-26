package com.example.MessageModels;

public class ChannelJoin implements msg {
    private String channel;
    private String user;
        
    public ChannelJoin(String channel, String user) {
        this.channel = channel;
        this.user = user;
    }
       
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
}
