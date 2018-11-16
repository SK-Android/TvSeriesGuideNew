package com.androidapp.mcs.tvseriesguide.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.androidapp.mcs.tvseriesguide.model.Rating;

import java.util.List;

@Dao
public interface RatingDao {

    @Insert
    void insertAllRatings(List<Rating> ratings);

    @Query("SELECT COUNT(*) from rating")
    int countItems();


    @Query("SELECT * FROM Rating")
    LiveData<List<Rating>> getAllRatings();
}
