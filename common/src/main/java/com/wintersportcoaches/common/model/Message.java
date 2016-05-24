package com.wintersportcoaches.common.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by artem on 24.05.16.
 */
public class Message {

    @SerializedName("id") String id;
    @SerializedName("creation_time") String  creationTime;
    @SerializedName("text") String text;
    @SerializedName("sender") String senderId;
    @SerializedName("chat") String chatId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
