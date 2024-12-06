/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author CLUB ACRICAIN
 * 
 */
public class Admin extends Person {
    private String username;
    private String password;

    // Constructor
    public Admin(int id, String name, String email, String telephone, LocalDate birthday, int cin, String address, String username, String password) {
        super(id, name, email, telephone, birthday, cin, address);
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
