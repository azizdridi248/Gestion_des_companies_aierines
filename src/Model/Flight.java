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

public class Flight {


    private int id;
    private String name;
    private String source;
    private String destination;
    private Date date;
    private String ecoSeat;
    private String businessSeat;
    private String classSeat;
    private FlightState state; // État du vol
public Flight(int id, String name, String source, String destination, Date date, 
              String ecoSeat, String businessSeat, String classSeat, FlightState state) {
    this.id = id;
    this.name = name;
    this.source = source;
    this.destination = destination;
    this.date = date;
    this.ecoSeat = ecoSeat;
    this.businessSeat = businessSeat;
    this.classSeat = classSeat;
    this.state = state;
}    // Constructor
    
    public Flight(int id, String name, String source, String destination) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
    }
    public Flight(int id, String name, String source, String destination, 
                  String ecoSeat, String businessSeat, String classSeat, FlightState state) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.ecoSeat = ecoSeat;
        this.businessSeat = businessSeat;
        this.classSeat = classSeat;
        this.state = state;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getEcoSeat() {
        return ecoSeat;
    }

    public void setEcoSeat(String ecoSeat) {
        this.ecoSeat = ecoSeat;
    }

    public String getBusinessSeat() {
        return businessSeat;
    }

    public void setBusinessSeat(String businessSeat) {
        this.businessSeat = businessSeat;
    }

    public String getClassSeat() {
        return classSeat;
    }

    public void setClassSeat(String classSeat) {
        this.classSeat = classSeat;
    }

    public FlightState getState() {
        return state;
    }

    public void setState(FlightState state) {
        this.state = state;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
