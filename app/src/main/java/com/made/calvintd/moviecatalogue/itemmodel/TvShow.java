package com.made.calvintd.moviecatalogue.itemmodel;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    private int firstAirDate;
    private int id;
    private int lastAirDate;
    private int name;
    private int numberOfEpisodes;
    private int numberOfSeasons;
    private int overview;
    private String posterPath;
    private int voteAverage;
    private int voteCount;

    public TvShow(int firstAirDate, int id, int lastAirDate, int name, int numberOfEpisodes, int numberOfSeasons, int overview, String posterPath,
                  int voteAverage, int voteCount) {
        this.firstAirDate = firstAirDate;
        this.id = id;
        this.lastAirDate = lastAirDate;
        this.name = name;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
        this.overview = overview;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public int getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(int firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(int lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getOverview() {
        return overview;
    }

    public void setOverview(int overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.firstAirDate);
        dest.writeInt(this.id);
        dest.writeInt(this.lastAirDate);
        dest.writeInt(this.name);
        dest.writeInt(this.numberOfEpisodes);
        dest.writeInt(this.numberOfSeasons);
        dest.writeInt(this.overview);
        dest.writeString(this.posterPath);
        dest.writeInt(this.voteAverage);
        dest.writeInt(this.voteCount);
    }

    protected TvShow(Parcel in) {
        this.firstAirDate = in.readInt();
        this.id = in.readInt();
        this.lastAirDate = in.readInt();
        this.name = in.readInt();
        this.numberOfEpisodes = in.readInt();
        this.numberOfSeasons = in.readInt();
        this.overview = in.readInt();
        this.posterPath = in.readString();
        this.voteAverage = in.readInt();
        this.voteCount = in.readInt();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
