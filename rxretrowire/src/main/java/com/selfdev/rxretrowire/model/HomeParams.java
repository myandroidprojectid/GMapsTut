package com.selfdev.rxretrowire.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rahul on 06/04/18.
 */

public class HomeParams {
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("limit")
    @Expose
    private String limit;

    public HomeParams(String genre, String name, String limit) {
        this.genre = genre;
        this.name = name;
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "HomeParams{" +
                "genre='" + genre + '\'' +
                ", name='" + name + '\'' +
                ", limit='" + limit + '\'' +
                '}';
    }
}
