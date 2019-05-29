package com.made.calvintd.moviecatalogue.itemmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowDetailsResponse {
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("created_by")
    private List<CreatedBy> createdBy;
    @SerializedName("episode_run_time")
    private List<Integer> episodeRunTime;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("genres")
    private List<Genres> genres;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("id")
    private int id;
    @SerializedName("in_production")
    private boolean inProduction;
    @SerializedName("languages")
    private List<String> languages;
    @SerializedName("last_air_date")
    private String lastAirDate;
    @SerializedName("last_episode_to_air")
    private LastEpisodeToAir lastEpisodeToAir;
    @SerializedName("name")
    private String name;
    @SerializedName("next_episode_to_air")
    private String nextEpisodeToAir;
    @SerializedName("networks")
    private List<Networks> networks;
    @SerializedName("number_of_episodes")
    private int numberOfEpisodes;
    @SerializedName("number_of_seasons")
    private int numberOfSeasons;
    @SerializedName("origin_country")
    private List<String> originCountry;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_name")
    private String originalName;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("production_companies")
    private List<ProductionCompanies> productionCompanies;
    @SerializedName("seasons")
    private List<Seasons> seasons;
    @SerializedName("status")
    private String status;
    @SerializedName("type")
    private String type;
    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("vote_count")
    private int voteCount;

    public TvShowDetailsResponse(String backdropPath, List<CreatedBy> createdBy, List<Integer> episodeRunTime, String firstAirDate,
                                 List<Genres> genres, String homepage, int id, boolean inProduction, List<String> languages, String lastAirDate,
                                 LastEpisodeToAir lastEpisodeToAir, String name, String nextEpisodeToAir, List<Networks> networks,
                                 int numberOfEpisodes, int numberOfSeasons, List<String> originCountry, String originalLanguage,
                                 String originalName, String overview, double popularity, String posterPath,
                                 List<ProductionCompanies> productionCompanies, List<Seasons> seasons, String status, String type,
                                 double voteAverage, int voteCount) {
        this.backdropPath = backdropPath;
        this.createdBy = createdBy;
        this.episodeRunTime = episodeRunTime;
        this.firstAirDate = firstAirDate;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.inProduction = inProduction;
        this.languages = languages;
        this.lastAirDate = lastAirDate;
        this.lastEpisodeToAir = lastEpisodeToAir;
        this.name = name;
        this.nextEpisodeToAir = nextEpisodeToAir;
        this.networks = networks;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
        this.originCountry = originCountry;
        this.originalLanguage = originalLanguage;
        this.originalName = originalName;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.productionCompanies = productionCompanies;
        this.seasons = seasons;
        this.status = status;
        this.type = type;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<CreatedBy> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(List<CreatedBy> createdBy) {
        this.createdBy = createdBy;
    }

    public List<Integer> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInProduction() {
        return inProduction;
    }

    public void setInProduction(boolean inProduction) {
        this.inProduction = inProduction;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public LastEpisodeToAir getLastEpisodeToAir() {
        return lastEpisodeToAir;
    }

    public void setLastEpisodeToAir(LastEpisodeToAir lastEpisodeToAir) {
        this.lastEpisodeToAir = lastEpisodeToAir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNextEpisodeToAir() {
        return nextEpisodeToAir;
    }

    public void setNextEpisodeToAir(String nextEpisodeToAir) {
        this.nextEpisodeToAir = nextEpisodeToAir;
    }

    public List<Networks> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Networks> networks) {
        this.networks = networks;
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

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<ProductionCompanies> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompanies> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<Seasons> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Seasons> seasons) {
        this.seasons = seasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    private class CreatedBy {
        @SerializedName("id")
        private int id;
        @SerializedName("credit_id")
        private String creditId;
        @SerializedName("name")
        private String name;
        @SerializedName("gender")
        private int gender;
        @SerializedName("profile_path")
        private String profilePath;

        public CreatedBy(int id, String creditId, String name, int gender, String profilePath) {
            this.id = id;
            this.creditId = creditId;
            this.name = name;
            this.gender = gender;
            this.profilePath = profilePath;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreditId() {
            return creditId;
        }

        public void setCreditId(String creditId) {
            this.creditId = creditId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getProfilePath() {
            return profilePath;
        }

        public void setProfilePath(String profilePath) {
            this.profilePath = profilePath;
        }
    }

    private class Genres {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;

        public Genres(int id, String name) {
            this.id = id;
            this.name = name;
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
    }

    private class LastEpisodeToAir {
        @SerializedName("air_date")
        private String airDate;
        @SerializedName("episode_number")
        private String episodeNumber;
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("overview")
        private String overview;
        @SerializedName("production_code")
        private String productionCode;
        @SerializedName("season_number")
        private int seasonNumber;
        @SerializedName("show_id")
        private int showId;
        @SerializedName("still_path")
        private String stillPath;
        @SerializedName("vote_average")
        private double voteAverage;
        @SerializedName("vote_count")
        private int voteCount;

        public LastEpisodeToAir(String airDate, String episodeNumber, int id, String name, String overview, String productionCode,
                                int seasonNumber, int showId, String stillPath, double voteAverage, int voteCount) {
            this.airDate = airDate;
            this.episodeNumber = episodeNumber;
            this.id = id;
            this.name = name;
            this.overview = overview;
            this.productionCode = productionCode;
            this.seasonNumber = seasonNumber;
            this.showId = showId;
            this.stillPath = stillPath;
            this.voteAverage = voteAverage;
            this.voteCount = voteCount;
        }

        public String getAirDate() {
            return airDate;
        }

        public void setAirDate(String airDate) {
            this.airDate = airDate;
        }

        public String getEpisodeNumber() {
            return episodeNumber;
        }

        public void setEpisodeNumber(String episodeNumber) {
            this.episodeNumber = episodeNumber;
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

        public String getProductionCode() {
            return productionCode;
        }

        public void setProductionCode(String productionCode) {
            this.productionCode = productionCode;
        }

        public int getSeasonNumber() {
            return seasonNumber;
        }

        public void setSeasonNumber(int seasonNumber) {
            this.seasonNumber = seasonNumber;
        }

        public int getShowId() {
            return showId;
        }

        public void setShowId(int showId) {
            this.showId = showId;
        }

        public String getStillPath() {
            return stillPath;
        }

        public void setStillPath(String stillPath) {
            this.stillPath = stillPath;
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
    }

    private class Networks {
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private int id;
        @SerializedName("logo_path")
        private String logoPath;
        @SerializedName("origin_country")
        private String originCountry;

        public Networks(String name, int id, String logoPath, String originCountry) {
            this.name = name;
            this.id = id;
            this.logoPath = logoPath;
            this.originCountry = originCountry;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogoPath() {
            return logoPath;
        }

        public void setLogoPath(String logoPath) {
            this.logoPath = logoPath;
        }

        public String getOriginCountry() {
            return originCountry;
        }

        public void setOriginCountry(String originCountry) {
            this.originCountry = originCountry;
        }
    }

    private class ProductionCompanies {
        @SerializedName("id")
        private int id;
        @SerializedName("logo_path")
        private String logoPath;
        @SerializedName("name")
        private String name;
        @SerializedName("origin_country")
        private String originCountry;

        public ProductionCompanies(int id, String logoPath, String name, String originCountry) {
            this.id = id;
            this.logoPath = logoPath;
            this.name = name;
            this.originCountry = originCountry;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogoPath() {
            return logoPath;
        }

        public void setLogoPath(String logoPath) {
            this.logoPath = logoPath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginCountry() {
            return originCountry;
        }

        public void setOriginCountry(String originCountry) {
            this.originCountry = originCountry;
        }
    }

    private class Seasons {
        @SerializedName("air_date")
        private String airDate;
        @SerializedName("episode_count")
        private int episodeCount;
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("overview")
        private String overview;
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("season_number")
        private int seasonNumber;

        public Seasons(String airDate, int episodeCount, int id, String name, String overview, String posterPath, int seasonNumber) {
            this.airDate = airDate;
            this.episodeCount = episodeCount;
            this.id = id;
            this.name = name;
            this.overview = overview;
            this.posterPath = posterPath;
            this.seasonNumber = seasonNumber;
        }

        public String getAirDate() {
            return airDate;
        }

        public void setAirDate(String airDate) {
            this.airDate = airDate;
        }

        public int getEpisodeCount() {
            return episodeCount;
        }

        public void setEpisodeCount(int episodeCount) {
            this.episodeCount = episodeCount;
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

        public int getSeasonNumber() {
            return seasonNumber;
        }

        public void setSeasonNumber(int seasonNumber) {
            this.seasonNumber = seasonNumber;
        }
    }
}
