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

public class Booking {
    private int id;
    private int idCustomer; // ID of the customer making the booking
    private int idFlight; // ID of the flight being booked
    private Seat seatClass; // Seat class for the booking
    private float price; // Price of the booking
    private Date date; // Booking date

    // Constructor
    public Booking(int id, int idCustomer, int idFlight, Seat seatClass, float price, Date date) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.idFlight = idFlight;
        this.seatClass = seatClass;
        this.price = price;
        this.date = date;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public Seat getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(Seat seatClass) {
        this.seatClass = seatClass;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Methods
    public void confirm() {
        System.out.println("Booking confirmed! Booking ID: " + id);
    }

    public void cancel() {
        System.out.println("Booking canceled! Booking ID: " + id);
    }

    // Print booking details
    public void printDetails() {
        System.out.println("Booking ID: " + id);
        System.out.println("Customer ID: " + idCustomer);
        System.out.println("Flight ID: " + idFlight);
        System.out.println("Seat Class: " + seatClass);
        System.out.println("Price: $" + price);
        System.out.println("Booking Date: " + date);
    }
}
