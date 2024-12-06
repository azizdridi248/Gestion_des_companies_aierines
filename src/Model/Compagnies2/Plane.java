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
    private int nbSeats;
    private PlaneEtat etat;

    public Plane(int id, String type, float maxDistance, int nbSeats, PlaneEtat etat) {
        this.id = id;
        this.type = type;
        this.maxDistance = maxDistance;
        this.nbSeats = nbSeats;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNbSeats() {
        return nbSeats;
    }

    public void setNbSeats(int nbSeats) {
        this.nbSeats = nbSeats;
    }

    public PlaneEtat getEtat() {
        return etat;
    }

    public void setEtat(PlaneEtat etat) {
        this.etat = etat;
    }
}


