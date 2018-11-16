package com.androidapp.mcs.tvseriesguide.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Details {


    @PrimaryKey
    @NonNull
    @SerializedName("imdbID")
    @Expose
    private String imdbID;

    @ColumnInfo
    @SerializedName("Title")
    @Expose
    private String title;

    @ColumnInfo
    @SerializedName("Year")
    @Expose
    private String year;

    @ColumnInfo
    @SerializedName("Rated")
    @Expose
    private String rated;

    @ColumnInfo
    @SerializedName("Released")
    @Expose
    private String released;

    @ColumnInfo
    @SerializedName("Season")
    @Expose
    private String season;

    @ColumnInfo
    @SerializedName("Episode")
    @Expose
    private String episode;

    @ColumnInfo
    @SerializedName("Runtime")
    @Expose
    private String runtime;

    @ColumnInfo
    @SerializedName("Genre")
    @Expose
    private String genre;

    @ColumnInfo
    @SerializedName("Director")
    @Expose
    private String director;

    @ColumnInfo
    @SerializedName("Writer")
    @Expose
    private String writer;

    @ColumnInfo
    @SerializedName("Actors")
    @Expose
    private String actors;

    @ColumnInfo
    @SerializedName("Plot")
    @Expose
    private String plot;

    @ColumnInfo
    @SerializedName("Language")
    @Expose
    private String language;

    @ColumnInfo
    @SerializedName("Country")
    @Expose
    private String country;

    @ColumnInfo
    @SerializedName("Awards")
    @Expose
    private String awards;

    @ColumnInfo
    @SerializedName("Poster")
    @Expose
    private String poster;

    @Ignore
    @SerializedName("Ratings")
    @Expose
    private List<Rating> ratings = null;

    @ColumnInfo
    @SerializedName("Metascore")
    @Expose
    private String metascore;

    @ColumnInfo
    @SerializedName("imdbRating")
    @Expose
    private String imdbRating;

    @ColumnInfo
    @SerializedName("imdbVotes")
    @Expose
    private String imdbVotes;

    @ColumnInfo
    @SerializedName("seriesID")
    @Expose
    private String seriesID;

    @ColumnInfo
    @SerializedName("Type")
    @Expose
    private String type;

    @ColumnInfo
    @SerializedName("Response")
    @Expose
    private String response;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Details(@NonNull String imdbID, String title, String year, String rated, String released, String season, String episode, String runtime, String genre, String director, String writer, String actors, String plot, String language, String country, String awards, String poster, String metascore, String imdbRating, String imdbVotes, String seriesID, String type, String response) {
        this.imdbID = imdbID;
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.season = season;
        this.episode = episode;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.poster = poster;
        this.metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.seriesID = seriesID;
        this.type = type;
        this.response = response;
    }
}
