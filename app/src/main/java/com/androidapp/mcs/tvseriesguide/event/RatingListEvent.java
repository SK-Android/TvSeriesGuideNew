package com.androidapp.mcs.tvseriesguide.event;

import com.androidapp.mcs.tvseriesguide.model.Rating;

import java.util.List;

public class RatingListEvent {

    private List<Rating> ratingList;

    public RatingListEvent(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }
}
