package main.java;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Catalog implements Search {

    Date lastUpdated;
    Map<String, List<Movie>> movieTitles;
    Map<String, List<Movie>> movieLanguages;
    Map<String, List<Movie>> movieGenres;
    Map<String, List<Movie>> movieReleaseDates;
    Map<String, List<Movie>> movieCities;

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<Movie> searchByTitle(String title){
        return movieTitles.get(title);
    }

    public List<Movie> searchByLanguage(String language){
        return movieTitles.get(language);
    }

    public List<Movie> searchByGenre(String genre){
        return movieTitles.get(genre);
    }

    public List<Movie> searchByReleaseDate(Date release_date){
        return movieTitles.get(release_date);
    }

    public List<Movie> searchByCity(String city){
        return movieTitles.get(city);
    }
}
