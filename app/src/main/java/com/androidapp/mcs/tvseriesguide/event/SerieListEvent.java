package com.androidapp.mcs.tvseriesguide.event;

import com.androidapp.mcs.tvseriesguide.model.Series;

import java.util.List;

public class SerieListEvent {
    //This class wraps a list of Series, Episode, etc. This kind of packaging is required for event bus
    //i.e simple pojo classes that are simple wrappers around the data I want to move around in the application
    private List<Series> seriesList;

    public SerieListEvent(List<Series> seriesList) {
        this.seriesList = seriesList;
    }


}
