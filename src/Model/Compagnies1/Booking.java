/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author CLUB ACRICAIN
 */

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import java.util.List;

public class Booking {




    private int idbooking;          // Booking ID
    private LocalDate date;
    private int customerId;
    private int flightId;
    private String Seat;
    private Seat typeSeat;
    public static HashMap<Integer, List<FlightBooking>> bookings = new HashMap<>();

    public Booking(int idbooking, LocalDate date, int customerId, int flightId, String Seat, Seat typeSeat) {
        this.idbooking = idbooking;
        this.date = date;
        this.customerId = customerId;
        this.flightId = flightId;
        this.Seat = Seat;
        this.typeSeat = typeSeat;
    }

    public String getSeat() {
        return Seat;
    }

    public void setSeat(String Seat) {
        this.Seat = Seat;
    }

    public Seat getTypeSeat() {
        return typeSeat;
    }

    public void setTypeSeat(Seat typeSeat) {
        this.typeSeat = typeSeat;
    }
    

    
    
    
    public int getIdbooking() {
        return idbooking;
    }

    public void setIdbooking(int idbooking) {
        this.idbooking = idbooking;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }



  

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Static method to retrieve all bookings
    public static HashMap<Integer, List<FlightBooking>> getBookings() {
        return bookings;
    }

    // Add a FlightBooking to a specific flight ID
    public static void addFlightBooking(int flightId, FlightBooking flightBooking) {
        bookings.computeIfAbsent(flightId, k -> new ArrayList<>()).add(flightBooking);
    }

    // Remove a FlightBooking from a specific flight ID
    public static boolean removeFlightBooking(int flightId, FlightBooking flightBooking) {
        if (bookings.containsKey(flightId)) {
            return bookings.get(flightId).remove(flightBooking);
        }
        return false;
    }

    // Retrieve all FlightBookings for a specific flight ID
    public static List<FlightBooking> getFlightBookings(int flightId) {
        return bookings.getOrDefault(flightId, new ArrayList<>());
    }
public void imprimer() {
    System.out.println("----- Booking Details -----");
    System.out.println("Booking ID: " + idbooking);
    System.out.println("Date: " + date);
    System.out.println("Customer ID: " + customerId);
    System.out.println("Flight ID: " + flightId);
    System.out.println("Seat: " + Seat);
    System.out.println("Type of Seat: " + (typeSeat != null ? typeSeat.toString() : "Not Specified"));
    System.out.println("---------------------------");
}

}