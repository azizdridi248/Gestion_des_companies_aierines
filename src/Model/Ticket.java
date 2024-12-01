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



    public String getDetails() {
        // Retourne une description du ticket
        return "Ticket ID: " + id + ", Type: " + typeTicket;
    }

    // Validation du ticket
    public boolean validate() {
        return typeTicket != null && !typeTicket.isEmpty();
    }

    // Classe interne pour représenter des détails supplémentaires
    public static class Details {
        private final String description;
        private final String priority;

        public Details(String description, String priority) {
            this.description = description;
            this.priority = priority;
        }

        public String getDescription() {
            return description;
        }

        public String getPriority() {
            return priority;
        }

   
    }
}