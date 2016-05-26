package com.wintersportcoaches.common.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by artem on 24.05.16.
 */
public class Message {

    @SerializedName("id") int id;
    @SerializedName("creation_time") String  creationTime;
    @SerializedName("text") String text;
    @SerializedName("sender") int senderId;
    @SerializedName("chat") int chatId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
}
