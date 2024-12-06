/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


/**
 *
 * @author CLUB ACRICAIN
 */
public record FlightBooking(Flight f,Customer customer, Seat seat) {

    public Customer getCustomer() {
        return customer;
    }

    public Seat getSeat() {
        return seat;
    }

    public Flight getF() {
        return f;
    }

}

