package com.androidapp.mcs.tvseriesguide.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity                         //Defines a single table in SQLite database
public class Series {

    @PrimaryKey
    @NonNull                        //Part of android.Support
    @SerializedName("imdbID")
    @Expose
    private String imdbID;

    @ColumnInfo
    @SerializedName("Title")
    @Expose
    private String title;

    @ColumnInfo
    @SerializedName("Year")
    @Expose
    private String year;

    @ColumnInfo                 //Turns below values into a SQLite column in a SQLite table
    @SerializedName("Type")
    @Expose
    private String type;

    @ColumnInfo
    @SerializedName("Poster")
    @Expose
    private String poster;

    @ColumnInfo
    @SerializedName("totalResults")
    @Expose
    private String totalResults;

    @ColumnInfo
    @SerializedName("Response")
    @Expose
    private String response;


    @Ignore  //Check***
    @SerializedName("Search")
    @Expose
    private List<Series> search = null;


    public List<Series> getSearch() {
        return search;
    }

    public void setSearch(List<Series> search) {
        this.search = search;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Ignore
    public Series() {
    }


    public Series(@NonNull String imdbID, String title, String year, String type, String poster, String totalResults, String response) {
        this.imdbID = imdbID;
        this.title = title;
        this.year = year;
        this.type = type;
        this.poster = poster;
        this.totalResults = totalResults;
        this.response = response;
    }

    @Override
    public String toString() {
        return "Series{" +
                "imdbID='" + imdbID + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", type='" + type + '\'' +
                ", poster='" + poster + '\'' +
                ", totalResults='" + totalResults + '\'' +
                ", response='" + response + '\'' +
                ", search=" + search +
                '}';
    }
}

