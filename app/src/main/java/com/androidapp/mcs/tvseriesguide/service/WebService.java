package com.androidapp.mcs.tvseriesguide.service;

import com.androidapp.mcs.tvseriesguide.model.Details;
import com.androidapp.mcs.tvseriesguide.model.Episode;
import com.androidapp.mcs.tvseriesguide.model.Series;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {
    String BASE_URL = "http://www.omdbapi.com/";
    String FEEDSERIES = "?apikey=2b0cf4b2&s=game&type=series";
    String FEED = "?apikey=2b0cf4b2";


//&i=tt0944947 game of thrones id
//    www.omdbapi.com/?apikey=2b0cf4b2&i=tt0944947&season=1


    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //Call to get a list of series
    @GET(FEEDSERIES)
    Call<Series> getSeries();

//
//    @GET("/?apikey=" + API_KEY)
//    Call<EpisodeResponse> getEpisodes(@Query("i") String id, @Query("SEASON") String SEASON);


    @GET(FEED)
    Call<Episode> getEpisodes(
            @Query("i") String id, @Query("season") String season);

    //www.omdbapi.com/?apikey=2b0cf4b2&i=tt0944947&i=tt0944947&season=1


    @GET(FEED)
    Call<Details> getDetails(
            @Query("i") String id, @Query("season") String season, @Query("episode") String episode);
    //http://www.omdbapi.com/?apikey=2b0cf4b2&i=tt0944947&i=tt0944947&season=1&episode=1

//    @GET(FEED)
//    Call<Season> getSeasons(@Query("IMDBID") String IMDBID);
//http://www.omdbapi.com/?apikey=2b0cf4b2&i=tt0944947
    //    @GET("?apikey=2b0cf4b2&season=1")
//    Call<Episode> getSeasonCount(@Query("IMDBID") String IMDBID);
//
    //Call to get a list of episodes with a value of Total Seasons
//    @GET("?apikey=2b0cf4b2")
//    Call<Episode> getEpisodes(@Query("IMDBID") String IMDBID, @Query("SEASON") String SEASON);
}
