/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
/**
 *
 * @author CLUB ACRICAIN
 */

import java.util.EnumMap;
import java.util.Date;

public class Flight {

    private int id;
    private Date date;
    private double capacity;
    private String airlineName;
    private float duration;
    private String destination;
    private EnumMap<Seat, Integer> seatsBooked; // Gestion des sièges réservés par classe
    private FlightState state; // État du vol
    private float promotion;

    // Constructeur
    public Flight() {
        this.seatsBooked = new EnumMap<>(Seat.class);
        for (Seat seatClass : Seat.values()) {
            seatsBooked.put(seatClass, 0); // Initialiser avec 0 réservation
        }
        this.state = FlightState.SCHEDULED; // État par défaut
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public FlightState getState() {
        return state;
    }

    public void setState(FlightState state) {
        this.state = state;
    }

    public float getPromotion() {
        return promotion;
    }

    public void setPromotion(float promotion) {
        this.promotion = promotion;
    }

    // Gestion des réservations par classe
    public int getSeatsBooked(Seat seatClass) {
        return seatsBooked.getOrDefault(seatClass, 0);
    }

    public void incrementSeatsBooked(Seat seatClass) {
        seatsBooked.put(seatClass, seatsBooked.get(seatClass) + 1);
    }

    public void decrementSeatsBooked(Seat seatClass) {
        if (seatsBooked.get(seatClass) > 0) {
            seatsBooked.put(seatClass, seatsBooked.get(seatClass) - 1);
        }
    }

    // Afficher les réservations pour chaque classe
    public void printSeatBookings() {
        for (Seat seatClass : Seat.values()) {
            System.out.println(seatClass + ": " + getSeatsBooked(seatClass) + " seats booked");
        }
    }
}

    
