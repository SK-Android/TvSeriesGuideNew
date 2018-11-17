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

@Entity
public class EpisodeResponse implements Parcelable {

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
    @SerializedName("Released")
    @Expose
    private String released;

    @ColumnInfo
    @SerializedName("Episode")
    @Expose
    private String episode;

    @ColumnInfo
    @SerializedName("imdbRating")
    @Expose
    private String imdbRating;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }


    public EpisodeResponse(String title, String released, String episode, String imdbRating, String imdbID) {
        this.title = title;
        this.released = released;
        this.episode = episode;
        this.imdbRating = imdbRating;
        this.imdbID = imdbID;
    }

    @Ignore
    public EpisodeResponse() {
    }

    @Override
    public String toString() {
        return "Episode_{" +
                "title='" + title + '\'' +
                ", released='" + released + '\'' +
                ", episode='" + episode + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbID='" + imdbID + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.released);
        dest.writeString(this.episode);
        dest.writeString(this.imdbRating);
        dest.writeString(this.imdbID);
    }

    protected EpisodeResponse(Parcel in) {
        this.title = in.readString();
        this.released = in.readString();
        this.episode = in.readString();
        this.imdbRating = in.readString();
        this.imdbID = in.readString();
    }

    public static final Parcelable.Creator<EpisodeResponse> CREATOR = new Parcelable.Creator<EpisodeResponse>() {
        @Override
        public EpisodeResponse createFromParcel(Parcel source) {
            return new EpisodeResponse(source);
        }

        @Override
        public EpisodeResponse[] newArray(int size) {
            return new EpisodeResponse[size];
        }
    };
}
