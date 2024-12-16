package Controller;

import BD.DB;
import Model.Booking;
import Model.Customer;
import Model.Flight;
import Model.Seat;
import Model.SeatN;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BookingAddController implements Initializable, SeatN {

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
    private DatePicker birthdaycustomer;
    @FXML
    private TextField cincustomer;
    @FXML
    private TextField addresscustomer;
    @FXML
    private TextField passportcustomer;
    @FXML
    private TextField seat;
    @FXML
    private TextField idflight;
    @FXML
    private DatePicker iddate;
    @FXML
    private TextField idseat;

    private Connection conn = null;
    private PreparedStatement preparedstatement = null;
    private ResultSet rs = null; 
    private Customer customer = null;

    SeatN checkSeatAvailability = (seatInput, flightId) -> 
        BookingController.bookings.stream()
            .noneMatch(booking -> 
                booking.getSeat().equalsIgnoreCase(seatInput) && booking.getFlightId() == flightId
            );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize any required components if needed
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Booking.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void resetFormFields() {
        idcustomer.clear();
        nomcustomer.clear();
        emailcustomer.clear();
        telephonecustomer.clear();
        birthdaycustomer.setValue(null);
        cincustomer.clear();
        addresscustomer.clear();
        passportcustomer.clear();
        seat.clear();
        idflight.clear();
        iddate.setValue(null);
        idseat.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }

    @FXML
    private void add(ActionEvent event) {
        // Validate inputs
        if (idcustomer.getText().isEmpty() || nomcustomer.getText().isEmpty() || emailcustomer.getText().isEmpty()) {
            showAlert("Error", "Please fill in all mandatory fields!");
            return;
        }

        LocalDate customerBirthday = birthdaycustomer.getValue();
        if (customerBirthday == null) {
            showAlert("Error", "Please select a valid birthday!");
            return;
        }

        try {
            int idFlight = parseIntegerField(idflight, "Flight ID");
            String flightSeat = seat.getText();
            if (!countnbseatrestant(flightSeat, idFlight)) {
                showAlert("Error", "Seat " + flightSeat + " is already taken for Flight ID " + idFlight + "!");
                return;
            }

            LocalDate bookingDate = iddate.getValue();
            if (bookingDate == null) {
                showAlert("Error", "Please select a valid booking date!");
                return;
            }

            int customerId = parseIntegerField(idcustomer, "Customer ID");
            int customerCin = parseIntegerField(cincustomer, "CIN");
            Seat etat = Seat.valueOf(idseat.getText().toUpperCase());

            if (customerId == -1 || customerCin == -1 || idFlight == -1) {
                return; // Parsing error messages are already shown
            }

            // Create and add new customer
            Customer newCustomer = new Customer(
                customerId,
                nomcustomer.getText(),
                emailcustomer.getText().trim(),
                telephonecustomer.getText(),
                customerBirthday,
                customerCin,
                addresscustomer.getText(),
                passportcustomer.getText()
            );
            CustomerController.customerList.add(newCustomer);

            // Validate flight existence
            Flight flight = FlightController.flightList.stream()
                    .filter(f -> f.getId() == idFlight)
                    .findFirst()
                    .orElse(null);

            if (flight == null) {
                showAlert("Error", "Flight not found!");
                return;
            }
            System.out.println("Entered Flight ID: " + idFlight);

            // Create and add new booking
            int newBookingId = BookingController.bookings.size() + 4;
            Booking newBooking = new Booking(newBookingId, bookingDate, customerId, idFlight, flightSeat, etat);
            BookingController.bookings.add(newBooking);
                        insertIntoDatabase("INSERT INTO public.customer(id, name, email, telephone, birthday, cin, address, passport_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", 
                new Object[] {customerId, newCustomer.getName(), newCustomer.getEmail(), newCustomer.getTelephone(), java.sql.Date.valueOf(newCustomer.getBirthday()), customerCin, newCustomer.getAddress(), newCustomer.getPassportNumber()});

            // Insert into Database
            insertIntoDatabase("INSERT INTO public.booking(id_booking, date, customer_id, flight_id, seat, type_seat) VALUES (?, ?, ?, ?, ?, ?)", 
                new Object[] {newBooking.getIdbooking(), java.sql.Date.valueOf(bookingDate), customerId, idFlight, flightSeat, etat.name()});


            // Success message and reset form
            showAlert("Success", "Booking added successfully!");
            
            resetFormFields();

        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private int parseIntegerField(TextField field, String fieldName) {
        try {
            return Integer.parseInt(field.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", fieldName + " must be a valid number!");
            return -1; // Return an invalid value to indicate parsing error
        }
    }

    @Override
    public boolean countnbseatrestant(String seat, int flightId) {
        return checkSeatAvailability.countnbseatrestant(seat, flightId);
    }

    private void insertIntoDatabase(String sql, Object[] parameters) {
        conn = DB.connecter();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < parameters.length; i++) {
                pstmt.setObject(i + 1, parameters[i]);
            }
            pstmt.executeUpdate();
        } catch (Exception e) {
            showAlert("Error", "Database insertion failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
