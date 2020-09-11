package main.java;

public class Admin extends Person {

    private Show show;

    boolean addMovie(Movie movie);

    boolean modifyMovie(Movie movie);

    boolean addShow(Show show);

    boolean modifyShow(Show show);

    boolean cancelShow(Show show);

    boolean blockUser(Person person);
}
