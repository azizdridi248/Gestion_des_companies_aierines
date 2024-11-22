/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author 21655
 */
public record Ticket(int id, String typeTicket) {

    // Exemple de méthode personnalisée
    public String imprimir() {
        return "Impression du ticket ID: " + id + ", Type: " + typeTicket;
    }
    public String getDetails() {
        // Retourne une description du ticket
        return "Ticket ID: " + id + ", Type: " + typeTicket;
    }
    // Validation du ticket
    public boolean validate() {
        return typeTicket != null && !typeTicket.isEmpty();
    }
}