/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author CLUB ACRICAIN
 */
import java.time.LocalDateTime; // To handle date and time

public class Schedule extends Flight {

    private LocalDateTime departureTime; // Departure date and time
    private LocalDateTime arrivalTime;   // Arrival date and time
    private String terminal;            // Terminal information

    // Constructor
    public Schedule(int id, String name, String source, String destination, 
                    LocalDateTime departureTime, LocalDateTime arrivalTime, String terminal) {
        // Call the parent class constructor
        super(id, name, source, destination);
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.terminal = terminal;
    }

    // Getters and Setters
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    // Additional Method: Calculate flight duration
    public long getFlightDurationInMinutes() {
        if (departureTime != null && arrivalTime != null) {
            return java.time.Duration.between(departureTime, arrivalTime).toMinutes();
        } else {
            return -1; // Return -1 if times are not set
        }
    }
}
