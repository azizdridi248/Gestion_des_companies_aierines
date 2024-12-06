package Controller;

import static Controller.CustomerController.customerList;
import static Controller.FlightController.flightList;
import Model.Booking;
import Model.Customer;
import Model.Flight;
import Model.Seat;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        @FXML
    private TableColumn<Booking, String> seat;
    @FXML
    private TableColumn<Booking, Seat> typeseat;

    // Flight details labels
    @FXML
    private Label IDFLIGHT, NOMFLIGHT, SOURCEFLIGHT, DESTINATIONFLIGHT;
    @FXML
    private Label ECOSEATFLIGHT;
    @FXML
    private Label CLASSSEATFLIGHT;
    @FXML
    private Label BUISSESSSEATFLIGHT, STATEFLIGHT;

    // Customer details labels
    @FXML
    private Label IDCUSTOMER, NOMCUSTOMER, EMAILCUSTOMER, NTELEPHONE;
    @FXML
    private Label CINCUSTOMER, ADRESSCUSTOMER, PASSPORTCUSTOMER, BIRTHDAYCUSTOMER;

    // Observable list for bookings
    public static ObservableList<Booking> bookings=FXCollections.observableArrayList(new Booking(1, LocalDate.of(2024, 5, 15), 1, 1,"15F",Seat.FIRST_CLASS));
    
    // ImageView buttons for add, update, delete, and back
    @FXML
    private Button btnAdd;
    // ImageView buttons for add, update, delete, and back
    @FXML
    private Button btnDelete;
    // ImageView buttons for add, update, delete, and back
    @FXML
    private Button btnBack;
    @FXML
    private TableColumn<Booking, Void> ticket;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize table columns
        IdBooking.setCellValueFactory(new PropertyValueFactory<>("idbooking"));
        idcustomer.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        idflight.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        typeseat.setCellValueFactory(new PropertyValueFactory<>("TypeSeat"));
        addButtonToTable();


          table.setItems(bookings);
        resetFlightDetails();
        resetCustomerDetails();

        // Add listener to update details when a row is selected in the TableView
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateDetailsForSelectedBooking(newValue);
            }
        });
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
    private void addBooking(ActionEvent event) throws IOException {
                                 Parent root = FXMLLoader.load(getClass().getResource("/View/BookingAdd.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void deleteBooking(ActionEvent event) {
                Booking selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            bookings.remove(selected);
            table.refresh();
        }
        
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
                                 Parent root = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
private void addButtonToTable() {
    ticket.setCellFactory(column -> new javafx.scene.control.TableCell<Booking, Void>() {
        private final Button button = new Button("Imprimer");

        {
            button.setOnAction(event -> {
                Booking selected = getTableView().getItems().get(getIndex());
                if (selected != null) {
                    // Create an anonymous subclass of Booking
                    Booking customBooking = new Booking(
                        selected.getIdbooking(),
                        selected.getDate(),
                        selected.getCustomerId(),
                        selected.getFlightId(),
                        selected.getSeat(),
                        selected.getTypeSeat()
                    ) {
                        @Override
                        public void imprimer() {
                            try {
                                // Load the Ticket.fxml scene
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Ticket.fxml"));
                                Parent root = loader.load();

                                // Retrieve customer and flight details
                                Customer customer = customerList.stream()
                                        .filter(c -> c.getId() == selected.getCustomerId())
                                        .findFirst().orElse(null);

                                Flight flight = flightList.stream()
                                        .filter(f -> f.getId() == selected.getFlightId())
                                        .findFirst().orElse(null);

                                // Pass data to the TicketController
                                TicketController ticketController = loader.getController();
                                ticketController.Display(selected, flight, customer);

                                // Navigate to the Ticket.fxml scene
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                                System.out.println("Button clicked. Booking details printed.");
                            } catch (IOException ex) {
                                Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };

                    // Call the overridden imprimer method
                    customBooking.imprimer();
                } else {
                    System.out.println("No booking selected.");
                }
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(button);
            }
        }
    });

    // Add the action column to the TableView if not already added
    if (!table.getColumns().contains(ticket)) {
        table.getColumns().add(ticket);
    }
}


}
