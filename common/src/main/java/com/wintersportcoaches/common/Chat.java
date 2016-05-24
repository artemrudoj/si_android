package com.wintersportcoaches.common;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alexeykhatskevich on 24.05.16.
 */
public class Chat {
    @SerializedName("id")  int id;
    @SerializedName("name")  String name;
    @SerializedName("type")  int type;
    @SerializedName("creation_time")  String creationTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
