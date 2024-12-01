/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author CLUB ACRICAIN
 */
public class Plane {
    private int id;
        private String type;
    private float maxDistance;
    private int firstClassSeatNumber;
     private int buisnessSeatNumber;
    private int ecoClassSeatNumber;
    private PlaneEtat etat;

    public PlaneEtat getEtat() {
        return etat;
    }

    public void setEtat(PlaneEtat etat) {
        this.etat = etat;
    }

    public int getBuisnessSeatNumber() {
        return buisnessSeatNumber;
    }

    public void setBuisnessSeatNumber(int buisnessSeatNumber) {
        this.buisnessSeatNumber = buisnessSeatNumber;
    }
    public Plane(int id, String type, float maxDistance, int firstClassSeatNumber, 
                 int businessSeatNumber, int ecoClassSeatNumber, PlaneEtat etat) {
        this.id = id;
        this.type = type;
        this.maxDistance = maxDistance;
        this.firstClassSeatNumber = firstClassSeatNumber;
        this.buisnessSeatNumber = businessSeatNumber;
        this.ecoClassSeatNumber = ecoClassSeatNumber;
        this.etat = etat;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirstClassSeatNumber() {
        return firstClassSeatNumber;
    }

    public void setFirstClassSeatNumber(int firstClassSeatNumber) {
        this.firstClassSeatNumber = firstClassSeatNumber;
    }

    public int getEcoClassSeatNumber() {
        return ecoClassSeatNumber;
    }

    public void setEcoClassSeatNumber(int ecoClassSeatNumber) {
        this.ecoClassSeatNumber = ecoClassSeatNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(float maxDistance) {
        this.maxDistance = maxDistance;
    }
}

    

