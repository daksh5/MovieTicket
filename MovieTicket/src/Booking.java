package main.java;

import main.java.enums.BookingStatus;

import java.util.Date;

public class Booking {

    String bookingNumber;
    int numberOfSeats;
    Date createdOn;
    BookingStatus status;


    boolean cancelBooking(){
        return true;
    }
}
