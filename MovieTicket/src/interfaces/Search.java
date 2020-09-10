package main.java.interfaces;

import java.util.Date;

public interface Search {
    public String SearchByTitle(String title);
    public String SearchByLanguage(String language);
    public String SearchByGenre(String genre);
    public String SearchByReleaseDate(Date release_date);
    public String SearchByCity(String city);
}
