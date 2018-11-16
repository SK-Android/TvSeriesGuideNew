package com.androidapp.mcs.tvseriesguide.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.androidapp.mcs.tvseriesguide.model.Seasons;

import java.util.List;

@Dao
public interface SeasonsDao {

    @Insert
    void insertAllSeasons(List<Seasons> seasons);

    @Query("SELECT COUNT(*) from seasons")
    int countItems();

    @Query("SELECT * FROM Seasons")
    List<Seasons> getAllSeasons();
}
