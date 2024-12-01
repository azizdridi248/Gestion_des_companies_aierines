/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Booking;
import Model.Customer;
import Model.Flight;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author CLUB ACRICAIN
 */
public class BookingAddController implements Initializable {


    @FXML
    private Button idadd;
    @FXML
    private ImageView IDBACK;
    @FXML
    private TextField idcustomer;
        @FXML
    private TextField nomcustomer;

    @FXML
    private TextField emailcustomer;

        @FXML
    private TextField telephonecustomer;
    @FXML
    private TextField birthdaycustomer;
    @FXML
    private TextField cincustomer;
    @FXML
    private TextField addresscustomer;
    @FXML
    private TextField passportcustomer;
    private TextField seat;
    private TextField price;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void back(MouseEvent event) {
    }
    @FXML
private void add(MouseEvent event) {
    try {
        // Validate and retrieve inputs
        int customerId = Integer.parseInt(idcustomer.getText());
        String customerName = nomcustomer.getText();
        String customerEmail = emailcustomer.getText().trim();
        String customerTelephone = telephonecustomer.getText();
        LocalDate customerBirthday = LocalDate.parse(birthdaycustomer.getText());
        int customerCin = Integer.parseInt(cincustomer.getText());
        String customerAddress = addresscustomer.getText();
        String customerPassport = passportcustomer.getText();
        
        
        int flightSeat = Integer.parseInt(seat.getText());
        double flightPrice = Double.parseDouble(price.getText());
        LocalDate bookingDate = LocalDate.now(); // Booking date is set to the current date

        // Validate the data
        if (customerName.isEmpty() || customerEmail.isEmpty() || customerTelephone.isEmpty()) {
            showAlert("Error", "Please fill all mandatory fields!");
            return;
        }

        // Check if the customer already exists (using a mock or shared `customerList`)
        Customer customer = CustomerController.customerList.stream()
                .filter(c -> c.getId() == customerId)
                .findFirst()
                .orElseGet(() -> {
                    // If customer doesn't exist, create a new one and add it to the list
                    Customer newCustomer = new Customer(
                            customerId,
                            customerName,
                            customerEmail,
                            customerTelephone,
                            customerBirthday,
                            customerCin ,
                            customerAddress,
                            customerPassport
                            
                    );
                    CustomerController.customerList.add(newCustomer);
                    return newCustomer;
                });

        // Validate that a valid seat number was provided (mock or shared `flightList` can be checked here)
        Flight flight = FlightController.flightList.stream()
                .filter(f -> f.getId() == flightSeat)
                .findFirst()
                .orElse(null);

        if (flight == null) {
            showAlert("Error", "Flight not found!");
            return;
        }

        // Generate a new booking ID (can be a mock for now)
        int newBookingId = BookingController.bookings.size() + 1;

        // Create a new Booking instance
        Booking newBooking = new Booking(newBookingId, bookingDate, customerId, flightSeat);

        // Add the new booking to the ObservableList in `BookingController`
        BookingController.bookings.add(newBooking);

        // Optionally, show a success alert
        showAlert("Success", "Booking added successfully!");

        // Reset the form fields
        resetFormFields();
    } catch (NumberFormatException e) {
        showAlert("Error", "Invalid input! Please ensure all fields are filled correctly.");
    } catch (Exception e) {
        showAlert("Error", "An unexpected error occurred: " + e.getMessage());
    }
}

private void resetFormFields() {
    idcustomer.clear();
    nomcustomer.clear();
    emailcustomer.clear();
    telephonecustomer.clear();
    birthdaycustomer.clear();
    cincustomer.clear();
    addresscustomer.clear();
    passportcustomer.clear();
    seat.clear();
    price.clear();
}

private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setContentText(content);
    alert.show();
}

    @FXML
    private void add(ActionEvent event) {
    }

    
}
