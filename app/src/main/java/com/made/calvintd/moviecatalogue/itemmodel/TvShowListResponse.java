package com.made.calvintd.moviecatalogue.itemmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowListResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Results> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public TvShowListResponse(int page, List<Results> results, int totalPages, int totalResults) {
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    private class Results {
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("popularity")
        private double popularity;
        @SerializedName("id")
        private int id;
        @SerializedName("backdrop_path")
        private String backdropPath;
        @SerializedName("vote_average")
        private double voteAverage;
        @SerializedName("overview")
        private String overview;
        @SerializedName("first_air_date")
        private String firstAirDate;
        @SerializedName("origin_country")
        private List<String> originCountry;
        @SerializedName("genre_ids")
        private List<Integer> genreIds;
        @SerializedName("original_language")
        private String originalLanguage;
        @SerializedName("vote_count")
        private int voteCount;
        @SerializedName("name")
        private String name;
        @SerializedName("original_name")
        private String originalName;

        public Results(String posterPath, double popularity, int id, String backdropPath, double voteAverage, String overview,
                       String firstAirDate, List<String> originCountry, List<Integer> genreIds, String originalLanguage, int voteCount, String name,
                       String originalName) {
            this.posterPath = posterPath;
            this.popularity = popularity;
            this.id = id;
            this.backdropPath = backdropPath;
            this.voteAverage = voteAverage;
            this.overview = overview;
            this.firstAirDate = firstAirDate;
            this.originCountry = originCountry;
            this.genreIds = genreIds;
            this.originalLanguage = originalLanguage;
            this.voteCount = voteCount;
            this.name = name;
            this.originalName = originalName;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public double getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(double voteAverage) {
            this.voteAverage = voteAverage;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getFirstAirDate() {
            return firstAirDate;
        }

        public void setFirstAirDate(String firstAirDate) {
            this.firstAirDate = firstAirDate;
        }

        public List<String> getOriginCountry() {
            return originCountry;
        }

        public void setOriginCountry(List<String> originCountry) {
            this.originCountry = originCountry;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public void setGenreIds(List<Integer> genreIds) {
            this.genreIds = genreIds;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(int voteCount) {
            this.voteCount = voteCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginalName() {
            return originalName;
        }

        public void setOriginalName(String originalName) {
            this.originalName = originalName;
        }
    }
}
