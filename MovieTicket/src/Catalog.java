package main.java;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Catalog {

    Date lastUpdated;
    Map<String, List<Movie>> movieTitles;
    Map<String, List<Movie>> movieLanguages;
    Map<String, List<Movie>> movieGenres;
    Map<String, List<Movie>> movieReleaseDates;
    Map<String, List<Movie>> movieCities;
}
