package com.made.calvintd.moviecatalogue.itemmodel;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    private String firstAirDate;
    private int id;
    private String name;
    private String overview;
    private String posterPath;
    private double voteAverage;
    private int voteCount;

    public TvShow(String firstAirDate, int id, String name,  String overview, String posterPath, double voteAverage, int voteCount) {
        this.firstAirDate = firstAirDate;
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
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
        dest.writeString(this.firstAirDate);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.overview);
        dest.writeString(this.posterPath);
        dest.writeDouble(this.voteAverage);
        dest.writeInt(this.voteCount);
    }

    protected TvShow(Parcel in) {
        this.firstAirDate = in.readString();
        this.id = in.readInt();
        this.name = in.readString();
        this.overview = in.readString();
        this.posterPath = in.readString();
        this.voteAverage = in.readDouble();
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
