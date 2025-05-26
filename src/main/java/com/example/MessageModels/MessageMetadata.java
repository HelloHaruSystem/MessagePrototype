package com.example.MessageModels;

import java.util.List;
import java.util.Map;

public class MessageMetadata implements msg {
    private boolean dm;
    private List<String> participants;
    private Map<String, Object> extras;
       
    public boolean isDm() {
        return dm;
    }

    public void setDm(boolean dm) {
        this.dm = dm;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, Object> extras) {
        this.extras = extras;
    }
}
