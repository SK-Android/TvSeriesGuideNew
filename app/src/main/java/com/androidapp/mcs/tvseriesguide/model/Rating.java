package com.androidapp.mcs.tvseriesguide.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Rating {

    @PrimaryKey
    @NonNull
    @SerializedName("Value")
    @Expose
    private String value;

    @ColumnInfo
    @SerializedName("Source")
    @Expose
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Rating(@NonNull String value, String source) {
        this.value = value;
        this.source = source;
    }
}

