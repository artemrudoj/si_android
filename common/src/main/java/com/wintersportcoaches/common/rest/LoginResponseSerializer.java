package com.wintersportcoaches.common.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alexeykhatskevich on 24.05.16.
 */
public class LoginResponseSerializer {
    @SerializedName("hash") private String hash;
    @SerializedName("id") private String id;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
