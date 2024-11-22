/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author CLUB ACRICAIN
 */

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Booking {

    // Static HashMap to store FlightBookings by Customer ID
    public static HashMap<Integer, List<FlightBooking>> book = new HashMap<>();
    
    private int id;            // Booking ID
    private Date date;         // Booking date

    // Constructor
    public Booking(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    // Method to add a FlightBooking to a customer (using customer ID)
    public static void addFlightBooking(int i, FlightBooking flightBooking) {
        
        book.computeIfAbsent(i, k -> new ArrayList<>()).add(flightBooking);
    }

    
    // Getter for the bookings map (if needed)
    public static HashMap<Integer, List<FlightBooking>> getBookings() {
        return book;
    }
}
