package com.androidapp.mcs.tvseriesguide.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.androidapp.mcs.tvseriesguide.model.EpisodeResponse;

import java.util.List;

@Dao
public interface EpisodeResponseDao {

    @Insert
    void insertAllEpisodeResponses(List<EpisodeResponse> episodeResponses);

    @Query("SELECT COUNT(*) from episoderesponse")
    int countItems();

    @Query("SELECT * FROM EpisodeResponse")
    LiveData<List<EpisodeResponse>> getAllEpisodeResponse();
}
