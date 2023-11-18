package com.example.moviedb4;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVsDetail {

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("created_by")
    private List<Object> createdBy;  // You might want to create a Creator class

    @SerializedName("episode_run_time")
    private List<Integer> episodeRunTime;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("genres")
    private List<Genre> genres;

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
    private Episode lastEpisodeToAir;

    @SerializedName("name")
    private String name;

    @SerializedName("next_episode_to_air")
    private Object nextEpisodeToAir;  // You might want to create an Episode class

    @SerializedName("networks")
    private List<Object> networks;  // You might want to create a Network class

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
    private List<Object> productionCompanies;  // You might want to create a ProductionCompany class

    @SerializedName("production_countries")
    private List<Object> productionCountries;  // You might want to create a ProductionCountry class

    @SerializedName("seasons")
    private List<Season> seasons;

    @SerializedName("spoken_languages")
    private List<SpokenLanguage> spokenLanguages;

    @SerializedName("status")
    private String status;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("type")
    private String type;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

    // Add getters and setters as needed


    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public List<Object> getCreatedBy() {
        return createdBy;
    }

    public List<Integer> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getId() {
        return id;
    }

    public boolean isInProduction() {
        return inProduction;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public Episode getLastEpisodeToAir() {
        return lastEpisodeToAir;
    }

    public String getName() {
        return name;
    }

    public Object getNextEpisodeToAir() {
        return nextEpisodeToAir;
    }

    public List<Object> getNetworks() {
        return networks;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public List<Object> getProductionCompanies() {
        return productionCompanies;
    }

    public List<Object> getProductionCountries() {
        return productionCountries;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getType() {
        return type;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    // Inner classes for nested objects
    public static class Genre {
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        // Add getters and setters as needed

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class Episode {
        // Add fields for Episode
    }

    public static class Season {
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

        @SerializedName("vote_average")
        private double voteAverage;

        // Add getters and setters as needed


        public String getAirDate() {
            return airDate;
        }

        public int getEpisodeCount() {
            return episodeCount;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getOverview() {
            return overview;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public int getSeasonNumber() {
            return seasonNumber;
        }

        public double getVoteAverage() {
            return voteAverage;
        }
    }

    public static class SpokenLanguage {
        @SerializedName("english_name")
        private String englishName;

        @SerializedName("iso_639_1")
        private String iso6391;

        @SerializedName("name")
        private String name;

        // Add getters and setters as needed

        public String getEnglishName() {
            return englishName;
        }

        public String getIso6391() {
            return iso6391;
        }

        public String getName() {
            return name;
        }
    }
}

