/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controller;

/**
 *
 * @author CLUB ACRICAIN
 */
public interface Login {
    boolean authenticate(String username, String password);
    boolean logout();
    boolean resetPassword(String email);
    boolean Update (String username, String password);
    
}
