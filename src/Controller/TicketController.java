/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Customer;
import Model.Flight;
import Model.Booking;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CLUB ACRICAIN
 */
public class TicketController implements Initializable {

    @FXML
    private Label CustomerID;
    @FXML
    private Label nomcustomer;
    @FXML
    private Label destination;
    @FXML
    private Label BookingID;
    @FXML
    private Label SeatID;
    @FXML
    private Label typeseat;
    @FXML
    private Label Dateid;
    @FXML
    private Label Source;
    @FXML
    private Label passportID;
    @FXML
    private Label FlightID;
    @FXML
    private ImageView codeqr;
    @FXML
    private Button idback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void Dispaly(Booking b,Flight f, Customer c){
        

        
    }

    void Display(Booking selected, Flight flight, Customer customer) {
   if (selected == null || flight == null || customer == null) {
        System.out.println("One or more required parameters are null. Unable to display booking details.");
        return; // Exit method if any parameter is null
    }

    // Populate the UI with booking details
    CustomerID.setText(String.valueOf(selected.getCustomerId()));
    nomcustomer.setText(customer.getName());
    destination.setText(flight.getDestination());
    BookingID.setText(String.valueOf(selected.getIdbooking()));
    SeatID.setText(selected.getSeat());
    typeseat.setText(selected.getTypeSeat().toString());

    // Format the date for display
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    Dateid.setText(selected.getDate().format(dateFormatter));

    // Set flight and customer-related fields
    Source.setText(flight.getSource());
    passportID.setText(customer.getPassportNumber());
    FlightID.setText(String.valueOf(selected.getFlightId()));
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
                          Parent root = FXMLLoader.load(getClass().getResource("/View/Booking.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
