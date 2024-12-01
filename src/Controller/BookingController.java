package Controller;

import static Controller.CustomerController.customerList;
import static Controller.FlightController.flightList;
import Model.Booking;
import Model.Customer;
import Model.Flight;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookingController implements Initializable {

    @FXML
    private TableView<Booking> table;
    @FXML
    private TableColumn<Booking, Integer> IdBooking;
    @FXML
    private TableColumn<Booking, Integer> idcustomer;
    @FXML
    private TableColumn<Booking, Integer> idflight;
    @FXML
    private TableColumn<Booking, LocalDate> date;

    // Flight details labels
    @FXML
    private Label IDFLIGHT, NOMFLIGHT, SOURCEFLIGHT, DESTINATIONFLIGHT;
    @FXML
    private Label ECOSEATFLIGHT, CLASSSEATFLIGHT, BUISSESSSEATFLIGHT, STATEFLIGHT;

    // Customer details labels
    @FXML
    private Label IDCUSTOMER, NOMCUSTOMER, EMAILCUSTOMER, NTELEPHONE;
    @FXML
    private Label CINCUSTOMER, ADRESSCUSTOMER, PASSPORTCUSTOMER, BIRTHDAYCUSTOMER;

    // Observable list for bookings
    public static ObservableList<Booking> bookings;
    
    // ImageView buttons for add, update, delete, and back
    @FXML
    private ImageView idadd, idupdate, iddelete, idback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize table columns
        IdBooking.setCellValueFactory(new PropertyValueFactory<>("idbooking"));
        idcustomer.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        idflight.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Load bookings into the TableView
        loadBookings();
        
        // Reset customer and flight details initially
        resetFlightDetails();
        resetCustomerDetails();

        // Add listener to update details when a row is selected in the TableView
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateDetailsForSelectedBooking(newValue);
            }
        });
    }

    private void loadBookings() {
        // Sample data for bookings (replace with actual data)
        bookings = FXCollections.observableArrayList(
            new Booking(1, LocalDate.of(2024, 5, 15), 1, 1) // Example booking
        );
        table.setItems(bookings);
    }

    private void updateDetailsForSelectedBooking(Booking booking) {
        // Get customer and flight details based on the booking selected
        Customer customer = customerList.stream()
                .filter(c -> c.getId() == booking.getCustomerId())
                .findFirst().orElse(null);

        Flight flight = flightList.stream()
                .filter(f -> f.getId() == booking.getFlightId())
                .findFirst().orElse(null);

        // Update customer and flight details if found
        if (customer != null) {
            updateCustomerDetails(customer);
        } else {
            resetCustomerDetails();
        }

        if (flight != null) {
            updateFlightDetails(flight);
        } else {
            resetFlightDetails();
        }
    }

    private void updateCustomerDetails(Customer customer) {
        IDCUSTOMER.setText(String.valueOf(customer.getId()));
        NOMCUSTOMER.setText(customer.getName());
        EMAILCUSTOMER.setText(customer.getEmail());
        NTELEPHONE.setText(customer.getTelephone());
        CINCUSTOMER.setText(String.valueOf(customer.getCin()));
        ADRESSCUSTOMER.setText(customer.getAddress());
        PASSPORTCUSTOMER.setText(customer.getPassportNumber());
        BIRTHDAYCUSTOMER.setText(customer.getBirthday().toString());
    }

    private void updateFlightDetails(Flight flight) {
        IDFLIGHT.setText(String.valueOf(flight.getId()));
        NOMFLIGHT.setText(flight.getName());
        SOURCEFLIGHT.setText(flight.getSource());
        DESTINATIONFLIGHT.setText(flight.getDestination());
        ECOSEATFLIGHT.setText(String.valueOf(flight.getEcoSeat()));
        CLASSSEATFLIGHT.setText(String.valueOf(flight.getClassSeat()));
        BUISSESSSEATFLIGHT.setText(String.valueOf(flight.getBusinessSeat()));
        STATEFLIGHT.setText(flight.getState().toString());
    }

    private void resetCustomerDetails() {
        IDCUSTOMER.setText("");
        NOMCUSTOMER.setText("");
        EMAILCUSTOMER.setText("");
        NTELEPHONE.setText("");
        CINCUSTOMER.setText("");
        ADRESSCUSTOMER.setText("");
        PASSPORTCUSTOMER.setText("");
        BIRTHDAYCUSTOMER.setText("");
    }

    private void resetFlightDetails() {
        IDFLIGHT.setText("");
        NOMFLIGHT.setText("");
        SOURCEFLIGHT.setText("");
        DESTINATIONFLIGHT.setText("");
        ECOSEATFLIGHT.setText("");
        CLASSSEATFLIGHT.setText("");
        BUISSESSSEATFLIGHT.setText("");
        STATEFLIGHT.setText("");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }

    @FXML
    private void addbooking(MouseEvent event) {
        try {
            int newBookingId = bookings.size() + 1; // New Booking ID
            int customerId = Integer.parseInt(IDCUSTOMER.getText().trim());
            int flightId = Integer.parseInt(IDFLIGHT.getText().trim());
            LocalDate bookingDate = LocalDate.now(); // Or retrieve from a date picker

            // Validate customer and flight existence
            Customer customer = customerList.stream()
                    .filter(c -> c.getId() == customerId)
                    .findFirst().orElse(null);
            Flight flight = flightList.stream()
                    .filter(f -> f.getId() == flightId)
                    .findFirst().orElse(null);

            if (customer == null) {
                showAlert("Error", "Customer not found!");
                return;
            }
            if (flight == null) {
                showAlert("Error", "Flight not found!");
                return;
            }

            // Create a new Booking object
            Booking newBooking = new Booking(newBookingId, bookingDate, customerId, flightId);

            // Add new booking to ObservableList
            bookings.add(newBooking);

            // Refresh the TableView
            table.setItems(bookings);

            // Show success alert
            showAlert("Success", "Booking added successfully!");

            // Optionally reset form fields
            resetCustomerDetails();
            resetFlightDetails();

        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input! Please ensure all fields are filled correctly.");
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    private void updatebooking(MouseEvent event) {
        // Update logic to be implemented
    }

    @FXML
    private void deletebooking(MouseEvent event) {
        // Delete logic to be implemented
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        // Back logic to be implemented
                         Parent root = FXMLLoader.load(getClass().getResource("/View/Planeadd.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
