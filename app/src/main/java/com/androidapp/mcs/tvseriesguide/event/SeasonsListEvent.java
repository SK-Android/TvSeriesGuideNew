package com.androidapp.mcs.tvseriesguide.event;

import com.androidapp.mcs.tvseriesguide.model.Seasons;

import java.util.List;

public class SeasonsListEvent {
    private List<Seasons> seasonsList;

    public SeasonsListEvent(List<Seasons> seasonsList) {
        this.seasonsList = seasonsList;
    }

}
