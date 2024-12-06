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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Schedule extends Flight {

    private LocalDateTime departureTime; // Departure date and time
    private LocalDateTime arrivalTime;   // Arrival date and time
    private String terminal; 
    private Crew crew;



    // Constructor
    public Schedule(int id, String name, String source, String destination, 
                    LocalDateTime departureTime, LocalDateTime arrivalTime, String terminal) {
        // Call the parent class constructor
        super(id, name, source, destination);
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.terminal = terminal;
    }

    public Schedule(LocalDateTime departureTime, LocalDateTime arrivalTime, int id, String name, String source, String destination) {
        super(id, name, source, destination);
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
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




 public class Crew {
        private final StringProperty pilote;

        public Crew(String pilote) {
            this.pilote = new SimpleStringProperty(pilote);
        }

        public String getPilote() {
            return pilote.get();
        }

        public void setPilote(String pilote) {
            this.pilote.set(pilote);
        }

        public StringProperty piloteProperty() {
            return pilote;
        }
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }
}

