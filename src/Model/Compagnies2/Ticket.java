/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Compagnies2;

/**
 *
 * @author 21655
 */
public record Ticket(int id, String typeTicket) {

    public boolean validate() {
        return typeTicket != null && !typeTicket.isEmpty();
    }

    
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