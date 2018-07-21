package com.clevmania.keia.model;

import java.io.Serializable;

public class ChatMessage implements Serializable {
    private String id;
    private String msg;

    public ChatMessage() {
    }

    public ChatMessage(String id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
