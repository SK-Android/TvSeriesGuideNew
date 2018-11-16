package com.androidapp.mcs.tvseriesguide.event;

import com.androidapp.mcs.tvseriesguide.model.EpisodeResponse;

import java.util.List;

public class EpisodeResponseListEvent {

    private List<EpisodeResponse> episodeResponses;

    public EpisodeResponseListEvent(List<EpisodeResponse> episodeResponses) {
        this.episodeResponses = episodeResponses;
    }


}
