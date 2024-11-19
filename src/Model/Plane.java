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
    private int firstClassSeatNumber;
    private int ecoClassSeatNumber;
    private String type;
    private float maxDistance;

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

    

