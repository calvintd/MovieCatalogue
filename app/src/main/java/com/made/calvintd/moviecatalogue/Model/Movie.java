package com.made.calvintd.moviecatalogue.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private int poster;
    private String title;
    private String description;
    private String year;
    private String director;

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.poster);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.year);
        dest.writeString(this.director);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.poster = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.year = in.readString();
        this.director = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
