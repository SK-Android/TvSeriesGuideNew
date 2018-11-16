package com.androidapp.mcs.tvseriesguide.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Episode implements Parcelable {


    @PrimaryKey
    @NonNull
    @SerializedName("Title")
    @Expose
    private String title;

    @ColumnInfo
    @SerializedName("Season")
    @Expose
    private String season;

    @ColumnInfo
    @SerializedName("totalSeasons")
    @Expose
    private String totalSeasons;

    @Ignore
    @SerializedName("Episodes")
    @Expose
    private List<EpisodeResponse> episodes = null;

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

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(String totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public List<EpisodeResponse> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodeResponse> episodes) {
        this.episodes = episodes;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "title='" + title + '\'' +
                ", season='" + season + '\'' +
                ", totalSeasons='" + totalSeasons + '\'' +
                ", episodes=" + episodes +
                ", response='" + response + '\'' +
                '}';
    }

    public Episode(String title, String season, String totalSeasons, List<EpisodeResponse> episodes, String response) {
        this.title = title;
        this.season = season;
        this.totalSeasons = totalSeasons;
        this.episodes = episodes;
        this.response = response;
    }

    public Episode() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.response);
        dest.writeString(this.season);
        dest.writeString(this.totalSeasons);
        dest.writeList(this.episodes);
        dest.writeString(this.title);
    }

    protected Episode(Parcel in) {
        this.response = in.readString();
        this.season = in.readString();
        this.totalSeasons = in.readString();
        this.episodes = new ArrayList<EpisodeResponse>();
        in.readList(this.episodes, EpisodeResponse.class.getClassLoader());
        this.title = in.readString();
    }

    public static final Parcelable.Creator<Episode> CREATOR = new Parcelable.Creator<Episode>() {
        @Override
        public Episode createFromParcel(Parcel source) {
            return new Episode(source);
        }

        @Override
        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };
}
