package com.wintersportcoaches.common.model;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.annotations.SerializedName;

/**
 * Created by artem on 24.05.16.
 */
public class Message {


    private final static String EXTRA_ID = "Message.EXTRA_ID";
    private final static String EXTRA_CREATION_TIME = "Message.EXTRA_CREATION_TIME";
    private final static String EXTRA_TEXT = "Message.EXTRA_TEXT";
    private final static String EXTRA_SENDER = "Message.EXTRA_SENDER";
    private final static String EXTRA_CHAT = "Message.EXTRA_CHAT";
    @SerializedName("id") int id;
    @SerializedName("creation_time") String  creationTime;
    @SerializedName("text") String text;
    @SerializedName("sender") int senderId;
    @SerializedName("chat") int chatId;

    public Message() {

    }


    public Intent insertToIntent() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ID,id);
        intent.putExtra(EXTRA_CREATION_TIME,creationTime);
        intent.putExtra(EXTRA_TEXT,text);
        intent.putExtra(EXTRA_SENDER,senderId);
        intent.putExtra(EXTRA_CHAT,chatId);
        return intent;
    }

    public Message(String text) {
        this.text = text;
    }

    public Message(Intent intent) {
        Bundle extras = intent.getExtras();
        if(extras == null) {
            throw new IllegalArgumentException("can not found extras in intent");
        }
        id = extras.getInt(EXTRA_ID);
        creationTime = extras.getString(EXTRA_CREATION_TIME);
        text = extras.getString(EXTRA_TEXT);
        senderId = extras.getInt(EXTRA_SENDER);
        chatId = extras.getInt(EXTRA_CHAT);
    }

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
