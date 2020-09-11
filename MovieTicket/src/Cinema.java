package main.java;

public class Cinema {

    String name;
    int totalCinemaHalls;
    Address location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalCinemaHalls() {
        return totalCinemaHalls;
    }

    public void setTotalCinemaHalls(int totalCinemaHalls) {
        this.totalCinemaHalls = totalCinemaHalls;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }
}
