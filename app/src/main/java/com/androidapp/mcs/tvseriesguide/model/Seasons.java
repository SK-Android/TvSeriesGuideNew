package com.androidapp.mcs.tvseriesguide.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

@Entity
public class Seasons {

    @PrimaryKey
    @NonNull
    String itemId;

    @Ignore
    public Seasons() {
    }


    public Seasons(String itemId) {
        if (itemId == null) {
            itemId = UUID.randomUUID().toString();
        }
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "Seasons{" +
                "itemId='" + itemId + '\'' +
                '}';
    }
}
