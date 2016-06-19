package com.wintersportcoaches.common.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by artem on 29.05.16.
 */
public class Lesson {
    final static public int SNOWBOARD = 1;
    final static public int SKI = 2;

    @SerializedName("id")
    String id;
    @SerializedName("place_string")
    String place_string;
    @SerializedName("status")
    int status;
    @SerializedName("type")
    int type;
    @SerializedName("time_start")
    String startTime;
    @SerializedName("couch")
    String coach;
    @SerializedName("learner")
    String learner;

    public String getCoach() {
        return coach;
    }

    public String getId() {
        return id;
    }

    public String getPlace_string() {
        return place_string;
    }

    public int getStatus() {
        return status;
    }

    public int getType() {
        return type;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getLearner() {
        return learner;
    }
}
