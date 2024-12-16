/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import java.time.LocalDate;

/**
 *
 * @author CLUB ACRICAIN
 */

public class Customer extends Person {
    private String passportNumber;

    // Constructor
    public Customer(int id, String name, String email, String telephone, LocalDate birthday, int cin, String address, String passportNumber) {
        super(id, name, email, telephone, birthday, cin, address);
        this.passportNumber = passportNumber;
    }

    // Getters and Setters
    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
    
    @Override
    public void message() {

        
        System.out.println("Customer Details:");
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Telephone: " + getTelephone());
        System.out.println("CIN: " + getCin());
        System.out.println("Address: " + getAddress());
        System.out.println("Passport Number: " + passportNumber);
    }
}